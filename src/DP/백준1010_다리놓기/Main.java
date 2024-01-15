package DP.백준1010_다리놓기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    /**
     * nCr = n-1Cr-1 + n-1Cr
     */
    static int size, N, R;
    static int[][] dp = new int[30][30];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        size = Integer.parseInt(br.readLine());
        combination();
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            System.out.println(dp[R][N]);
        }
    }

    static void combination() {

        for (int i = 0; i < 30; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int i = 2; i < 30; i++) {
            for (int j = 1; j < i + 1 && i != j; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        // dp[n][r] = dp[n-1][r-1] + dp[n-1][r];
    }
}
