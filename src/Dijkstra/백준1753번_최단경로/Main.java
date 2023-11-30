package Dijkstra.백준1753번_최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int startNode = Integer.parseInt(br.readLine());

        dist = new int[V + 1];
        visited = new boolean[V + 1];
        graph = new ArrayList[V + 1];

        for (int i = 0; i < V + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
        }

        dijkstra(startNode);
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                System.out.println(dist[i]);
            } else {
                System.out.println("INF");
            }
        }
        /*            */
    }

    static void dijkstra(int startNode) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startNode, 0));
        dist[startNode] = 0;

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if(visited[poll.vertex]) continue;
            visited[poll.vertex] = true;

            for (Node n : graph[poll.vertex]) {
                if (!visited[n.vertex]) {
                    dist[n.vertex] = Math.min(dist[n.vertex], dist[poll.vertex] + n.cost);
                    pq.add(new Node(n.vertex, dist[n.vertex]));
                }
            }
        }

    }


    static class Node implements Comparable<Node>{
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
