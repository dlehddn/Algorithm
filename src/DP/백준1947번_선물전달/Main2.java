package DP.백준1947번_선물전달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[1000001];

        dp[2] = 1;
        dp[3] = 2;

        for (int i = 4; i <= N; i++) {
            long tmp = ((long) (i - 1) * (dp[i - 1] + dp[i - 2])) % 1000000000;
            dp[i] = (int) tmp;
        }

        System.out.println(dp[N]);
    }
}
