package DP.백준2749번_피보나치수3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] dp;
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());
        dp = new int[1500001];
        long newN = N % 1500000;
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= 1500000; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000;
        }

        System.out.println(dp[(int) newN]);
    }

}









