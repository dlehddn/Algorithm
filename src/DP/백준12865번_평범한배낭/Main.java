package DP.백준12865번_평범한배낭;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /**
     * (2024/03/11)
     * 못 풀었다.
     */

    static int N, K;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];
        Arrays.fill(dp, -1);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            dp[w] = v;
        }

        int maxValue = memorization(K);
        System.out.println(maxValue);

    }

    static int memorization(int num) {
        if (num < 3) {
            if (dp[num] == -1) {
                return dp[num] = 0;
            } else {
                return dp[num];
            }
        }
        if(dp[num] != -1) return dp[num];

        int left = 1;
        int right = num - 1;
        int max = Integer.MIN_VALUE;
        while (left <= right) {
            dp[left] = memorization(left);
            dp[right] = memorization(right);
            if (dp[left] + dp[right] > max) {
                max = dp[left] + dp[right];
            }
            left++;
            right--;
        }
        return dp[num] = max;
    }
}
