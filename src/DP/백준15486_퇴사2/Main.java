package DP.백준15486_퇴사2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int dp[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());
            try {
                dp[i] = Math.max(dp[i - 1], dp[i]);
                dp[i + T] = Math.max(dp[i + T], dp[i] + P);
            } catch (ArrayIndexOutOfBoundsException e) {}
        }
        int result = Arrays.stream(dp)
                .max()
                .orElse(0);

        System.out.println(result);

    }
}
