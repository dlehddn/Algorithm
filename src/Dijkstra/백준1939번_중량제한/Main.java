package Dijkstra.백준1939번_중량제한;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n2.cost - n1.cost);
    static List<Node>[] graph;
    static int N, M, start, end;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new List[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[a].add(new Node(b, cost, 0));
            graph[b].add(new Node(a, cost, 0));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra());
    }

    static int dijkstra() {
        pq.add(new Node(start, Integer.MAX_VALUE, 0));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if (poll.vertex == end) {
                return poll.cost;
            }

            if (visited[poll.vertex]) continue;
            visited[poll.vertex] = true;

            for (Node next : graph[poll.vertex]) {
                int nextCost = next.cost < poll.cost ? next.cost : poll.cost;
                pq.add(new Node(next.vertex, nextCost, 0));
            }
        }

        return 0;
    }

    static class Node {
        int vertex, cost, min;

        public Node(int vertex, int cost, int min) {
            this.vertex = vertex;
            this.cost = cost;
            this.min = min;
        }
    }
}
