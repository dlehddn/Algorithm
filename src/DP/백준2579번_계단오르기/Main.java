package DP.백준2579번_계단오르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        try {
            dp[0] = arr[0];
            dp[1] = arr[0] + arr[1];
            dp[2] = Math.max(arr[0] + arr[2], arr[1] + arr[2]);
        } catch (Exception e) {}

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]);


        }

        System.out.println(dp[N-1]);

    }

}
