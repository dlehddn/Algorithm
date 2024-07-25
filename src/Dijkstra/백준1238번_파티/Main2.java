package Dijkstra.백준1238번_파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int N, M, X;
    static List<Edge>[] pos, negs;
    static int[] pDist, nDist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        pos = new List[N + 1];
        negs = new List[N + 1];
        pDist = new int[N + 1];
        nDist = new int[N + 1];

        Arrays.fill(pDist, Integer.MAX_VALUE);
        Arrays.fill(nDist, Integer.MAX_VALUE);

        pDist[X] = 0;
        nDist[X] = 0;

        for (int i = 1; i < N + 1; i++) {
            pos[i] = new ArrayList<>();
            negs[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pos[s].add(new Edge(e, c));
            negs[e].add(new Edge(s, c));
        }

        dijkstra(pos, pDist);
        dijkstra(negs, nDist);

        System.out.println(findMax());
    }

    static int findMax() {
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < N + 1; i++) {
            int sum = pDist[i] + nDist[i];
            if (sum > max) {
                max = sum;
            }
        }
        return max;
    }

    static void dijkstra(List<Edge>[] graph, int[] dist) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        boolean[] visited = new boolean[N + 1];
        pq.add(new Edge(X, 0));

        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            if (visited[poll.vertex]) continue;
            visited[poll.vertex] = true;

            for (Edge edge : graph[poll.vertex]) {
                pq.add(new Edge(edge.vertex, edge.cost + poll.cost));
                dist[edge.vertex] = Math.min(dist[edge.vertex], edge.cost + poll.cost);
            }
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
