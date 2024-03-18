package DP.백준1446번_지름길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] dp;
    static int N, D;
    static List<Load> loads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        dp = new int[D + 1];
        loads = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            loads.add(new Load(s, e, c));
        }
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        for (int i = 1; i <= D; i++) {
            dp[i] = dp[i - 1] + 1;
            for (Load load : loads) {
                if (load.end == i) {
                    dp[i] = Math.min(dp[i], dp[load.start] + load.cost);
                }
            }
        }

        System.out.println(dp[D]);


    }

    static class Load {
        int start, end, cost;

        public Load(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
