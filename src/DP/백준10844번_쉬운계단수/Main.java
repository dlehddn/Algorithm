package DP.백준10844번_쉬운계단수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1][10];

        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j != 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
                if (j != 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                dp[i][j] %= 1000000000;
            }
        }

        long result = 0;
        for (int i : dp[N]) {
            result += i;
        }

        System.out.println(result % 1000000000);
    }
}
