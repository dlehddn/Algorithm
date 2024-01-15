package Dijkstra.백준1800번_인터넷설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, P, K;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra();
        if(dist[1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else System.out.println(dist[1]);
    }

    static void dijkstra() {
        PriorityQueue<Route> pq = new PriorityQueue<>((r1, r2) -> Integer.compare(r1.minCost, r2.minCost));
        Route init = new Route(new ArrayList<>(), 0, N);
        pq.add(init);

        while (!pq.isEmpty()) {
            Route poll = pq.poll();
            if(visited[poll.vertex]) continue;
            visited[poll.vertex] = true;

            for (Node node : graph[poll.vertex]) {
                Route tmp = new Route(new ArrayList<>(poll.routes), poll.minCost, node.vertex);
                tmp.routes.add(node.cost);
                tmp.update();
                if (dist[node.vertex] > tmp.minCost) {
                    dist[node.vertex] = tmp.minCost;
                    pq.add(new Route(new ArrayList<>(tmp.routes), tmp.minCost, tmp.vertex));
                }
            }
        }

    }

    static class Node {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    static class Route {
        ArrayList<Integer> routes;
        int minCost;
        int vertex;

        public Route(ArrayList<Integer> routes, int minCost, int vertex) {
            this.routes = routes;
            this.minCost = minCost;
            this.vertex = vertex;
        }

        void update() {
            if (routes.size() <= K) {
                minCost = 0;
            } else {
                routes.sort(Comparator.reverseOrder());
                minCost = routes.stream()
                        .skip(K)
                        .mapToInt(n -> n)
                        .max()
                        .orElse(-1);
            }
        }
    }
}
