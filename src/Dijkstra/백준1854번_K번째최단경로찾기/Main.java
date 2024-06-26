package Dijkstra.백준1854번_K번째최단경로찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[] dist, visited;
    static List<Edge>[] graph;
    static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        graph = new List[N + 1];
        visited = new int[N + 1];
        Arrays.fill(dist, -1);
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(b, c));
        }

        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge poll = pq.poll();

            visited[poll.end]++;
            if (visited[poll.end] <= K) {
                dist[poll.end] = poll.value;
            } else {
                continue;
            }

            for (Edge edge : graph[poll.end]) {
                pq.add(new Edge(edge.end, poll.value + edge.value));
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (visited[i] < K) {
                dist[i] = -1;
            }
            System.out.println(dist[i]);
        }



    }

    static class Edge {
        int end, value;

        public Edge(int end, int value) {
            this.end = end;
            this.value = value;
        }
    }
}
