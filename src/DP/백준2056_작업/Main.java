package DP.백준2056_작업;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer>[] graph;
    static int[] costArr;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        costArr = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            costArr[i] = cost;
            if (num != 0) {
                for (int j = 0; j < num; j++) {
                    int node = Integer.parseInt(st.nextToken());
                    graph[i].add(node);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            dp[i] = graph[i].stream()
                    .mapToInt(n -> dp[n])
                    .max()
                    .orElse(0) + costArr[i];
        }

        int result = Arrays.stream(dp)
                .max()
                .orElse(-1);

        System.out.println(result);

    }
}
