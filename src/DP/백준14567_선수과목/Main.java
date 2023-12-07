package DP.백준14567_선수과목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[] graph;
    static int N, M;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int cur = Integer.parseInt(st.nextToken());
            graph[cur].add(before);
        }

        for (int i = 1; i <= N; i++) {
            if (graph[i].size() != 0) {
                int next = graph[i].stream()
                        .mapToInt(n -> dp[n])
                        .max()
                        .orElse(-1) + 1;
                dp[i] = next;
            } else {
                dp[i] = 1;
            }
        }

        Arrays.stream(dp)
                .skip(1)
                .forEach(n -> System.out.print(n + " "));
    }

}
