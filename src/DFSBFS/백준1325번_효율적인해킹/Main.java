package DFSBFS.백준1325번_효율적인해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();
    static int N, M, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        max = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[b].add(a);
        }

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }
        System.out.println(sb.toString());
    }

    static void bfs(int start) {
        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            cnt++;

            for (Integer next : graph[poll]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }
        if (max <= cnt) {
            if (max < cnt) {
                sb = new StringBuilder();
                max = cnt;
            }
            sb.append(start).append(" ");
        }
    }
}
