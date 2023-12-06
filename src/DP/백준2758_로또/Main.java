package DP.백준2758_로또;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/**
 * <2023-12-06>
 * 좀 드러운 문제다;;
 * 블로그에 잘 정리하자
 */
public class Main {

    static int T, N, M;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        dp = new long[11][2001];
        Arrays.stream(dp)
                .forEach(arr -> Arrays.fill(arr, -1));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            long result = 0;
            for (int j = M; j / Math.pow(2, N - 1) >= 1; j--) {
                result += findLotto(N, j);
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    static long findLotto(int depth, int pick) {
        if (depth == 1) {
            return 1;
        }
        if (dp[depth][pick] != -1) {
            return dp[depth][pick];
        }
        dp[depth][pick] = 0;
        for(int i = pick / 2; i / Math.pow(2, depth - 2) >= 1; i--) {
            dp[depth][pick] += findLotto(depth - 1, i);
        }
        return dp[depth][pick];
    }
}
