package DP.백준2758_로또;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * <2023-12-06>
 * 좀 드러운 문제다;;
 * 블로그에 잘 정리하자
 */
public class Main2 {

    static int T, N, M;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        dp = new long[11][2001];
        StringBuilder sb = new StringBuilder();

        dp[0][0] = 1;
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 2000; j++) {
                for (int k = j / 2; k >= 0; k--) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        /**
         *
         */
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            long result = 0;
            for (int j = M; j >= Math.pow(2, N - 1); j--) {
                result += dp[N][j];
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

}
