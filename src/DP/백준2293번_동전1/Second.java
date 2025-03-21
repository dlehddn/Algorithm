package DP.백준2293번_동전1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Second {
    static int[] arr;
    static int[] dp;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        dp[0] = 1;

        for (int coin : arr) {
            for (int i = coin; i < K + 1; i++) {
                dp[i] += dp[i - coin];
            }
        }
        System.out.println(dp[K]);
    }
}
