package Comb_Per.백준1256번_사전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K;
    static int[][] dp;
    static int kind;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + M + 1][N + 1];
        makeDpArr(N + M, N);
        kind = dp[N + M][N];

        if (kind < K) {
            System.out.println(-1);
            return;
        }

        makeString(N + M, N, K);
        System.out.println(sb);
    }

    static int makeDpArr(int n, int r) {
        if (dp[n][r] != 0) {
            return dp[n][r];
        }
        if (n == r || r == 0) {
            dp[n][r] = 1;
            return 1;
        }
        dp[n][r] = Math.min(makeDpArr(n-1, r-1) + makeDpArr(n-1, r), 1000000000);
        return dp[n][r];

    }

    static void makeString(int n, int m, int k) {
        if (n == 0) return;
        if (m == 0) {
            for (int i = 0; i < n; i++) {
                sb.append("z");
            }
            return;
        }
        if (n == m) {
            for (int i = 0; i < n; i++) {
                sb.append("a");
            }
            return;
        }
        if (k <= dp[n - 1][m - 1]) {
            sb.append("a");
            makeString(n - 1, m - 1, k);
        } else {
            sb.append("z");
            makeString(n-1, m, k - dp[n - 1][m - 1]);
        }
    }
}