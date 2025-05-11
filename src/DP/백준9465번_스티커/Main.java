package DP.백준9465번_스티커;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static int[][] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            arr = new int[2][N];
            StringTokenizer st;
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp = new int[2][N];
            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];
            for (int i = 1; i < N; i++) {
                if (i == 1) {
                    dp[0][1] = dp[1][0] + arr[0][1];
                    dp[1][1] = dp[0][0] + arr[1][1];
                } else {
                    dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
                    dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];
                }
            }
            result.append(Arrays.stream(dp)
                    .flatMapToInt(arr -> Arrays.stream(arr))
                    .max().orElse(0))
                    .append("\n");
        }
        System.out.println(result);
    }
}
