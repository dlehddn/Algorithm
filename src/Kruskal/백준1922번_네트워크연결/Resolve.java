package Kruskal.백준1922번_네트워크연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Resolve {

    static int N, M, result, count;
    static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(start, end, cost));
        }

        while (true) {
            Edge poll = pq.poll();
            if (find(poll.start) != find(poll.end)) {
                count++;
                result += poll.cost;
                union(poll.start, poll.end);
            }
            if (count == N - 1) {
                System.out.println(result);
                return;
            }
        }

    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    static int find(int node) {
        if (parent[node] == node) {
            return node;
        }
        return find(parent[node]);
    }

    static class Edge {
        int start, end, cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
