package DP.백준2747_피보나치수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        Arrays.fill(dp, -1);
        System.out.println(fibo(N));
    }
    static int fibo(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        if (dp[N] != -1) {
            return dp[N];
        }
        dp[N] = fibo(N - 1) + fibo(N - 2);
        return dp[N];
    }
}
