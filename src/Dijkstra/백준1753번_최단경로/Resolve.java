package Dijkstra.백준1753번_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Resolve {

    static int V, E, K; // 1 <= V <= 20,000 & 1 <= E <= 300,000
    static List<Edge>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());
        graph = new List[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, -1);
        initResource(br);
        dijkstra();
        printResult();
    }

    static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            sb.append(dist[i] == -1 ? "INF" : dist[i]);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static void dijkstra() {
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        pq.add(new Edge(K, 0));

        while (!pq.isEmpty()) {
            Edge poll = pq.poll();

            if (visited[poll.vertex]) continue;
            visited[poll.vertex] = true;
            dist[poll.vertex] = poll.cost;

            for (Edge next : graph[poll.vertex]) {
                pq.add(new Edge(next.vertex, poll.cost + next.cost));
            }
        }
    }

    static void initResource(BufferedReader br) throws IOException {
        StringTokenizer st;
        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Edge(end, cost));
        }

        for (int i = 1; i <= V; i++) {
            graph[i].sort((v1, v2) -> {
                if (v1.vertex == v2.vertex) {
                    return v1.cost - v2.cost;
                } else {
                    return v1.vertex - v2.vertex;
                }
            });
        }
    }

    static class Edge {
        int vertex, cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
