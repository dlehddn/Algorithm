package DP.백준9461번_파도반수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int T, N;
    static long[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        while (T > 0) {
            N = Integer.parseInt(br.readLine());
            dp = new long[101];
            dp[1] = 1;
            dp[2] = 1;
            dp[3] = 1;
            dp[4] = 2;
            dp[5] = 2;
            for (int i = 6; i <= N; i++) {
                dp[i] = dp[i - 1] + dp[i - 5];
            }
            result.append(dp[N]).append("\n");
            T--;
        }
        System.out.println(result);
    }
}
