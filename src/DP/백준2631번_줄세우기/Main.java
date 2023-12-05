package DP.백준2631번_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static int[] dp;
    static int[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];
        map = new int[N + 1];

        for (int i = 1; i < N+1; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i < N + 1; i++) {
            int maxValue = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (map[i] > map[j] && dp[j] > maxValue) {
                    maxValue = dp[j];
                }
            }
            dp[i] = maxValue + 1;
        }
        int result = Arrays.stream(dp)
                .max()
                .orElse(0);

        System.out.println(N-result);
    }
}
