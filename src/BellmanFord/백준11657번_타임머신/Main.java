package BellmanFord.백준11657번_타임머신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Node> graph;
    static int N, M;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dist = new long[N + 1];
        graph = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.add(new Node(start, end, cost));
        }

        for (int i = 1; i < N + 1; i++) {
            if (i == 1) {
                dist[i] = 0;
                continue;
            }
            dist[i] = Long.MAX_VALUE;
        }


        for (int i = 1; i <= N; i++) {
            for (Node node : graph) {
                if (node.end == 0) continue;
                if (dist[node.start] == Long.MAX_VALUE) continue;

                if (dist[node.end] > dist[node.start] + node.cost) {
                    dist[node.end] = dist[node.start] + node.cost;

                    if (i == N) {
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            System.out.println(dist[i] != Long.MAX_VALUE ? dist[i] : -1);
        }

    }

    static class Node {
        int start, end, cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
