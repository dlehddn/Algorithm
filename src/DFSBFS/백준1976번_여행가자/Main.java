package DFSBFS.백준1976번_여행가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static int[][] graph;
    static boolean[] visited;
    static String result = "YES";
    static int[] schedule;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        schedule = new int[M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    graph[i][j] = 1;
                }
            }
        }

        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            schedule[i] = Integer.parseInt(tmp[i]);
        }

        for (int i = 0; i < M - 1; i++) {
            visited = new boolean[N + 1];
            boolean go = bfs(schedule[i], schedule[i + 1]);
            if(schedule[i] == schedule[i + 1]) continue;
            if (!go) {
                result = "NO";
                break;
            }
        }

        System.out.println(result);
    }

    static boolean bfs(int start, int end) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            visited[poll] = true;
            for (int i = 1; i < N + 1; i++) {
                if (graph[poll][i] == 1 && !visited[i]) {
                    q.add(i);
                    if(i == end) return true;
                }
            }
        }
        return false;
    }
}
