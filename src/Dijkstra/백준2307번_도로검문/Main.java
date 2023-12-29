package Dijkstra.백준2307번_도로검문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visited;
    static int[] status;
    static int N, M, initResult, answer;
    static Pair disConnection;
    static ArrayList<Pair> routes;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];
        status = new int[N + 1];
        routes = new ArrayList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end, cost));
            graph[end].add(new Node(start, cost));
        }

        disConnection = new Pair(-1, -1);
        dijkstra();
        initResult = dist[N];
        backTrack(N);

        for (Pair p : routes) {
            disConnection = new Pair(p.start, p.end);
            visited = new boolean[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dijkstra();
            if (dist[N] == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            if (dist[N] - initResult > answer) {
                answer = dist[N] - initResult;
            }
        }
        System.out.println(answer);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if (visited[poll.vertex]) continue;
            visited[poll.vertex] = true;

            for (Node node : graph[poll.vertex]) {
                if (disConnection.start == poll.vertex && disConnection.end == node.vertex) continue;
                if (dist[node.vertex] > poll.cost + node.cost) {
                    dist[node.vertex] = poll.cost + node.cost;
                    pq.add(new Node(node.vertex, dist[node.vertex]));
                    status[node.vertex] = poll.vertex;
                }
            }
        }
    }

    static void backTrack(int lastNode) {
        if (lastNode == 1) return;

        routes.add(new Pair(status[lastNode], lastNode));
        backTrack(status[lastNode]);
    }

    static class Node {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }

    static class Pair {
        int start;
        int end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
