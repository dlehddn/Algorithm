package BellmanFord.백준1865번_웜홀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Edge> graph;
    static int T, N, M, W;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        loop:
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            dist = new long[N + 1];
//            Arrays.fill(dist, Long.MAX_VALUE);
//            dist[1] = 0;

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph.add(new Edge(s, e, t));
                graph.add(new Edge(e, s, t));
            }

            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());
                graph.add(new Edge(s, e, t * -1));
            }

//            for (int loop = 1; loop <= N; loop++) {
//                dist[loop - 1] = Long.MAX_VALUE;
//                dist[loop] = 0;
                for (int j = 1; j <= N; j++) {
                    for (Edge edge : graph) {
                        if (dist[edge.start] == Long.MAX_VALUE) continue;

                        if (dist[edge.end] > dist[edge.start] + edge.cost) {
                            dist[edge.end] = dist[edge.start] + edge.cost;

                            if (j == N) {
                                System.out.println("YES");
                                continue loop;
                            }

                        }
                    }
                }
//            }


            System.out.println("NO");
        }

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
