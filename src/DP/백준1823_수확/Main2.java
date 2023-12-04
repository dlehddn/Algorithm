package DP.백준1823_수확;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main2 {

    static int[] map;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1];
        dp = new int[N + 2][N + 2];
        Arrays.stream(dp)
                .forEach(arr -> Arrays.fill(arr, -1));

        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(findMax(1, N, 1));
    }

    static int findMax(int start, int end, int count) {
        if (start == end) {
            return count * map[start];
        }
        if (dp[start][end] != -1) {
            return dp[start][end];
        }
        dp[start][end] = Math.max(findMax(start+1, end, count+1) + count * map[start],
                findMax(start, end-1, count+1) + count * map[end]);
        return dp[start][end];
    }
}
