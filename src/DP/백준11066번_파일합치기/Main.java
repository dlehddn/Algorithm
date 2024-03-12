package DP.백준11066번_파일합치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    /**
     * 꼭 다시 풀어보자. 어렵게 푼 듯?
     */

    static int T, K;
    static int[] arr;
    static int[][] sumArr;
    static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[][] dp;

        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(br.readLine());
            arr = new int[K + 1];
            dp = new int[K + 1][K + 1];
            sumArr = new int[K + 1][K + 1];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < K + 1; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            findSumArr();
            for (int j = 0; j < K + 1; j++) {
                Arrays.fill(dp[j], INF);
            }
            sb.append(memorization(dp, arr, 1, K));
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }

    private static void findSumArr() {
        for (int i = 1; i < K + 1; i++) {
            sumArr[i][i] = arr[i];
            sumArr[0][i] = 0;
        }

        for (int i = 1; i < K + 1; i++) {
            for (int j = i + 1; j < K + 1; j++) {
                sumArr[i][j] = sumArr[i][j - 1] + arr[j];
            }
        }
    }

    private static int memorization(int[][] dp, int[] arr, int start, int end) {
        if (start + 1 == end) {
            return dp[start][end] = arr[start] + arr[end];
        }

        if (start == end) {
            return dp[start][end] = arr[start];
        }

        if (dp[start][end] != INF) {
            return dp[start][end];
        }

        int min = Integer.MAX_VALUE;
        for (int i = start; i < end; i++) {
            int sum = 0;
            if (i - start >= 1) {
                sum += sumArr[start][i];
            }
            if (end - (i + 1) >= 1) {
                sum += sumArr[i + 1][end];
            }
            int tmp = memorization(dp, arr, start, i) + memorization(dp, arr, i + 1, end) + sum;
            if (tmp < min) {
                min = tmp;
            }
        }
        return dp[start][end] = Math.min(dp[start][end], min);
    }
}
