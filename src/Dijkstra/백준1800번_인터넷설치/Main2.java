package Dijkstra.백준1800번_인터넷설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int N, P, K, min;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        min = Integer.MAX_VALUE;

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e, c));
            graph[e].add(new Edge(s, c));
        }

        binarySearch();

    }

    static void binarySearch() {
        int start = 0;
        int end = 1000000;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (dijkstra(mid)) {
                min = Math.min(min, mid);
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    static boolean dijkstra(int mid) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        boolean[] visited = new boolean[N + 1];

        pq.add(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge poll = pq.poll();

            if (visited[poll.vertex] || poll.cost > K) continue;
            visited[poll.vertex] = true;

            if (poll.vertex == N) {
                return true;
            }

            for (Edge edge : graph[poll.vertex]) {
                if (edge.cost > mid) {
                    pq.add(new Edge(edge.vertex, poll.cost + 1));
                } else {
                    pq.add(new Edge(edge.vertex, poll.cost));
                }
            }
        }
        return false;
    }

    static class Edge {
        int vertex, cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
