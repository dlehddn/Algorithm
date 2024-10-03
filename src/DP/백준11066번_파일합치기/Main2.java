package DP.백준11066번_파일합치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {

    static int T, K;
    static int[][] dp, sumArr;
    static List<Integer> list;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            list = new ArrayList<>();
            for (int j = 0; j < K; j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            min = Integer.MAX_VALUE;
            dp = new int[K][K];
            sumArr = new int[K][K];

            for (int j = 0; j < K; j++) {
                for (int k = 0; k < K; k++) {
                    dp[j][k] = 1000000000;
                    sumArr[j][k] = 1000000000;
                }
            }
            for (int j = 0; j < K; j++) {
                dp[j][j] = list.get(j);
                sumArr[j][j] = list.get(j);
            }
            for (int gap = 1; gap <= K - 1; gap++) {
                for (int j = 0; j + gap <= K - 1; j++) {
                    for (int k = 0; k < gap; k++) {
                        int tmp = dp[j][j + k] + dp[j + k + 1][j + gap];
                        dp[j][j + gap] = Math.min(tmp, dp[j][j + gap]);
                        tmp = dp[j][j + gap];
                        if (k != 0) tmp += sumArr[j][j + k];
                        if (gap - k - 1 != 0) tmp += sumArr[j + k + 1][j + gap];
                        sumArr[j][j + gap] = Math.min(sumArr[j][j + gap], tmp);
                    }
                }
            }
            sb.append(sumArr[0][K - 1]).append("\n");
        }
        System.out.println(sb);
    }
}