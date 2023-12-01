package DP.백준1823_수확;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 다시 풀어보기. 많이 헷갈린다.
 */
public class Main {

    static int[] map;
    static int N;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1];
        dp = new int[2002][2002];
        Arrays.stream(dp)
                .forEach(arr -> Arrays.fill(arr, -10000));

        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        dp[0][N+1] = 0;
        for (int i = N; i > 0; i--) {
            int cnt = N - i + 1;
            dp[0][i] = dp[0][i+1] + cnt * map[i];
        }
        for (int i = 1; i <= N; i++) {
            int cnt = i;
            dp[i][N+1] = dp[i-1][N+1] + cnt * map[i];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = N; j > i; j--) {
                int cnt = i + N - j + 1;
                dp[i][j] = Math.max(dp[i - 1][j] + cnt * map[i], dp[i][j + 1] + cnt * map[j]);
            }
        }

        int result = Arrays.stream(dp)
                .flatMapToInt(arr -> Arrays.stream(arr))
                .max()
                .orElse(-1);

        System.out.println(result);
    }
}
