package Dijkstra.백준5972번_택배배송;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Node>[] graph;
    static int[] dist;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        dist = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(cost, to));
            graph[to].add(new Node(cost, from));
        }

        dijkstra();
        System.out.println(dist[N]);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        boolean[] visited = new boolean[N + 1];
        dist[1] = 0;

        pq.add(new Node(0, 1));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if (visited[poll.vertex]) continue;
            visited[poll.vertex] = true;

            for (Node node : graph[poll.vertex]) {
                if (dist[node.vertex] > poll.cost + node.cost) {
                    pq.add(new Node(poll.cost + node.cost, node.vertex));
                    dist[node.vertex] = poll.cost + node.cost;
                }
            }
        }
    }

    static class Node {
        int cost, vertex;

        public Node(int cost, int vertex) {
            this.cost = cost;
            this.vertex = vertex;
        }
    }
}
