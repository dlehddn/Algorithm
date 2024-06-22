package DP.백준1947번_선물전달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N + 1];

        if (N == 1) {
            System.out.println(0);
            return;
        }

        dp[2] = 1;
        for (int i = 3; i < N + 1; i++) {
            dp[i] = (i - 1) * (dp[i - 2] + dp[i - 1]) % 1000000000;
        }
        System.out.println(dp[N]);
    }
}
