package DP.백준12865번_평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Jim> list;
    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        dp = new int[N][K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            list.add(new Jim(w, k));
        }
        list.sort((j1, j2) -> j1.w - j2.w);

        for (int i = 1; i <= K; i++) {
            if (i >= list.get(0).w) {
                dp[0][i] = list.get(0).v;
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= K; j++) {

                if (j < list.get(i).w) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], list.get(i).v + dp[i - 1][j - list.get(i).w]);
                }
            }
        }

        System.out.println(dp[N - 1][K]);
    }

    static class Jim {
        int w, v;

        public Jim(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
}
