package Dijkstra.백준1956번_운동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int V, E;
    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new List[V + 1];
        for (int i = 1; i < V + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a].add(new Edge(c, b));
        }

        System.out.println(dijkstra());
    }

    static int dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        boolean[][] visited = new boolean[V + 1][V + 1];
        for (int i = 1; i < V + 1; i++) {
            pq.add(new Edge(0, i, i));
        }

        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            if (poll.cost != 0 && poll.start == poll.vertex) {
                return poll.cost;
            }
            if (visited[poll.start][poll.vertex]) continue;
            visited[poll.start][poll.vertex] = true;

            for (Edge next : graph[poll.vertex]) {
                pq.add(new Edge(poll.cost + next.cost, next.vertex, poll.start));
            }
        }

        return -1;
    }

    static class Edge {
        int cost, vertex, start;

        public Edge(int cost, int vertex) {
            this.cost = cost;
            this.vertex = vertex;
        }

        public Edge(int cost, int vertex, int start) {
            this.cost = cost;
            this.vertex = vertex;
            this.start = start;
        }
    }

}
