package SWEA.test;

import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    private static int T, N, M;
	public static void main(String args[]) throws Exception
	{
        //System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        StringBuilder sb;
		for(int test_case = 1; test_case <= T; test_case++)
		{
            sb = new StringBuilder();
			st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int[] n_arr = new int[N];
            int[] m_arr = new int[M];

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                n_arr[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < M; i++) {
                m_arr[i] = Integer.parseInt(st.nextToken());
            }

            int temp = 0;
            int maxValue = Integer.MIN_VALUE;

            for(int i = 0; i <= Math.max(N, M) - Math.min(N, M); i++) {
             	temp = 0;
				if(N < M) {
                 	  for(int j = 0; j < N; j++) {
                	  	temp += n_arr[j] * m_arr[j + i];
                      }
                } else {
                 	  for(int j = 0; j < M; j++) {
                	  	temp += n_arr[j + i] * m_arr[j];
                      }
                }
                if(temp > maxValue) maxValue = temp;
            }
            sb.append("#" + test_case + " " + maxValue);
            System.out.println(sb);
		}
	}
}