package DP.백준1149번_RGB거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, result;
    static RGB[] rgbs;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rgbs = new RGB[N];
        dp = new int[N][3];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            rgbs[i] = new RGB(r, g, b);
        }

        dp[0][0] = rgbs[0].r;
        dp[0][1] = rgbs[0].g;
        dp[0][2] = rgbs[0].b;

        for (int i = 1; i < N; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgbs[i].r;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgbs[i].g;
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgbs[i].b;
        }

        System.out.println(Arrays.stream(dp[N-1])
                .min()
                .orElse(-1));
    }

    static class RGB {
        int r, g, b;

        public RGB(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
}
