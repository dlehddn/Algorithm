package SWEA.보물상자비밀번호;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("src/SWEA/보물상자비밀번호/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int N, K;
        String[] arr;
        ArrayList<Long> list;

        for(int tc = 1; tc <= T; tc++) {
            list = new ArrayList<>();
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new String[N];
            String line = br.readLine();
            for (int i = 0; i < line.length(); i++) {
                arr[i] = line.substring(i, i + 1);
            }

            for(int k = 0; k < N / 4; k++) {
                arr = changeDir(arr);


                for (int i = 0; i <= N - N / 4; i += N / 4) {
                    String hex = "";
                    for (int j = 0; j < N / 4; j++) {
                        hex += arr[i+j];
                    }
                    long num = Long.parseLong(hex, 16);
                    if (!list.contains(num)) {
                        list.add(num);
                    }
                }
            }
            Collections.sort(list, Collections.reverseOrder());
            sb.append("#" + tc + " " + list.get(K - 1));
            System.out.println(sb);
        }


    }

    private static String[] changeDir(String[] cur) {
        String[] nextArr = new String[cur.length];
        for (int i = 0; i < cur.length; i++) {
            if (i == 0) {
                nextArr[i] = cur[cur.length - 1];
            } else {
                nextArr[i] = cur[i - 1];
            }
        }
        return nextArr;
    }
}