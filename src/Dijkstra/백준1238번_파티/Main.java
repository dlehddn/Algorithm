package Dijkstra.백준1238번_파티;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] posGraph, nagGraph;
    static int[] posDist, nagDist, result;
    static int N, M, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        posGraph = new ArrayList[N + 1];
        nagGraph = new ArrayList[N + 1];
        posDist = new int[N + 1];
        nagDist = new int[N + 1];
        result = new int[N + 1];
        Arrays.fill(posDist, Integer.MAX_VALUE);
        Arrays.fill(nagDist, Integer.MAX_VALUE);

        for (int i = 0; i <= N; i++) {
            posGraph[i] = new ArrayList<>();
            nagGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            posGraph[start].add(new Node(end, cost));
            nagGraph[end].add(new Node(start, cost));
        }

        dijkstra(posDist, posGraph);
        dijkstra(nagDist, nagGraph);

        for (int i = 0; i <= N; i++) {
            result[i] = posDist[i] + nagDist[i];
        }

        int maxValue = Arrays.stream(result)
                .max()
                .orElse(-1);
        System.out.println(maxValue);
    }

    static void dijkstra(int[] dist, ArrayList<Node>[] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        boolean[] visited = new boolean[N + 1];

        pq.add(new Node(X, 0));
        dist[X] = 0;

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if (visited[poll.vertex]) continue;
            visited[poll.vertex] = true;

            for (Node node : graph[poll.vertex]) {
                if (dist[node.vertex] > poll.cost + node.cost) {
                    dist[node.vertex] = poll.cost + node.cost;
                    pq.add(new Node(node.vertex, dist[node.vertex]));
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
