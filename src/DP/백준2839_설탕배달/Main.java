package DP.백준2839_설탕배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];

        for (int i = 3; i < N + 1; i++) {
            if(i == 3) dp[i] = 1;
            if(i == 5) dp[i] = 1;
            try {
                if (dp[i - 3] == 0 && dp[i - 5] != 0) {
                    dp[i] = dp[i - 5] + 1;
                } else if (dp[i - 3] != 0 && dp[i - 5] == 0) {
                    dp[i] = dp[i - 3] + 1;
                } else if (dp[i - 3] != 0 && dp[i - 5] != 0) {
                    dp[i] = Math.min(dp[i - 3], dp[i - 5]) + 1;
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
        }
        if (dp[N] != 0) {
            System.out.println(dp[N]);
        } else {
            System.out.println(-1);
        }
    }
}
