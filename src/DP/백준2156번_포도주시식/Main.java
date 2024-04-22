package DP.백준2156번_포도주시식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] dp;
    static int[] arr;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = arr[0];

        for (int i = 1; i < N; i++) {
            if (i == 1) {
                dp[1] = arr[0] + arr[1];
                continue;
            }

            if (i == 2) {
                dp[2] = Math.max(Math.max(arr[0] + arr[2], arr[1] + arr[2]), arr[0] + arr[1]);
                continue;
            }

            dp[i] = Math.max(Math.max(dp[i - 2] + arr[i], dp[i - 3] + arr[i - 1] + arr[i]), dp[i-1]);
        }

        System.out.println(Arrays.stream(dp)
                .max().orElse(-1));


    }


}
