package DFSBFS.백준1260번_DFS와BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, V;
    static List<Integer>[] graph;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            graph[to].add(from);
        }

        sb = new StringBuilder();
        dfs(V, new boolean[N + 1]);
        sb.append("\n");
        bfs(V);
        System.out.println(sb.toString());
    }

    static void dfs(int node, boolean[] visited) {
        sb.append(node + " ");
        visited[node] = true;

        Collections.sort(graph[node]);
        for (Integer nextNode : graph[node]) {
            if (!visited[nextNode]) {
                dfs(nextNode, visited);
            }
        }
    }

    static void bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        q.add(node);
        visited[node] = true;

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            sb.append(poll + " ");

            Collections.sort(graph[poll]);
            for (Integer nextNode : graph[poll]) {
                if (!visited[nextNode]) {
                    q.add(nextNode);
                    visited[nextNode] = true;
                }
            }
        }
    }
}
