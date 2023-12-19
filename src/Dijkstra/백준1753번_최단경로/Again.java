package Dijkstra.백준1753번_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Again {

    static ArrayList<Node>[] list;
    static int[] dist;
    static boolean[] visited;
    static int V, E, START;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        START = Integer.parseInt(br.readLine());

        list = new ArrayList[V + 1];
        dist = new int[V + 1];
        visited = new boolean[V + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < V + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[u].add(new Node(v, w));
        }

        dijkstra();
        Arrays.stream(dist)
                .skip(1)
                .forEach(n -> {
                    if (n != Integer.MAX_VALUE) {
                        System.out.println(n);
                    } else {
                        System.out.println("INF");
                    }
                });

    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.add(new Node(START, 0));
        dist[START] = 0;

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            visited[poll.vertex] = true;

            for (Node nextNode : list[poll.vertex]) {
                if (dist[nextNode.vertex] > poll.cost + nextNode.cost) {
                    dist[nextNode.vertex] = poll.cost + nextNode.cost;
                    if(!visited[nextNode.vertex]) {
                        pq.add(new Node(nextNode.vertex, dist[nextNode.vertex]));
                    }
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
}
