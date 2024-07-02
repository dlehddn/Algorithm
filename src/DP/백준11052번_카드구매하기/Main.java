package DP.백준11052번_카드구매하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];
        Arrays.fill(dp, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = arr[1];

        for (int i = 2; i <= N; i++) {
            dp[i] = arr[i];
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], dp[i - j] + arr[j]);
            }
        }

        System.out.println(dp[N]);

    }
}
