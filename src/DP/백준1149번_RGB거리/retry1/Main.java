package DP.백준1149번_RGB거리.retry1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr, dp;
    static int N;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        memorization(0, 0);

        System.out.println(Arrays.stream(dp[0])
                .min()
                .orElse(-1));

    }

    static int memorization(int idx, int n) {
        if (n == N - 1) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                if (i == idx) continue;
                if (arr[N - 1][i] < min) {
                    min = arr[N - 1][i];
                }
            }
            dp[n][idx] = min;
            return min;
        }

        for (int i = 0; i < 3; i++) {
            if (n != 0 && i == idx) continue;
            if (dp[n][i] != Integer.MAX_VALUE) continue;
            dp[n][i] = memorization(i, n + 1) + arr[n][i];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i == idx) continue;
            if (dp[n][i] < min) {
                min = dp[n][i];
            }
        }

        return min;
    }
}
