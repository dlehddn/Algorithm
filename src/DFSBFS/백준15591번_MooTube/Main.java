package DFSBFS.백준15591번_MooTube;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Edge>[] graph;
    static int N, Q;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[n1].add(new Edge(n2, cost));
            graph[n2].add(new Edge(n1, cost));
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            bfs(k, v);
        }

        System.out.println(sb.toString());

    }

    static void bfs(int k, int v) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        visited[v] = true;
        int cnt = 0;

        while (!q.isEmpty()) {
            Integer poll = q.poll();

            for (Edge next : graph[poll]) {
                if (next.cost >= k && !visited[next.vertex]) {
                    cnt++;
                    q.add(next.vertex);
                    visited[next.vertex] = true;
                }
            }

        }

        sb.append(cnt + "\n");
    }

    static class Edge {
        int vertex, cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
