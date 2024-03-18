package DP.백준13305번_주유소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] dist, price, dp;
    static long totalPrice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dist = new int[N - 1];
        price = new int[N];
        dp = new int[N - 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = price[0];
        for (int i = 1; i < N - 1; i++) {
            dp[i] = Math.min(dp[i - 1], price[i]);
        }

        for (int i = 0; i < N - 1; i++) {
            totalPrice += (long) dp[i] * dist[i];
        }
        System.out.println(totalPrice);
    }
}
