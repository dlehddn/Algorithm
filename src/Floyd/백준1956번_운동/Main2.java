package Floyd.백준1956번_운동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int V, E, min;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new int[V + 1][V + 1];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < V + 1; i++) {
            for (int j = 0; j < V + 1; j++) {
                graph[i][j] = 99999999;
            }
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
        }

        // 경유지
        for (int i = 1; i < V + 1; i++) {
            // 출발지
            for (int j = 0; j < V + 1; j++) {
                // 도착지
                for (int k = 0; k < V + 1; k++) {
                    graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
                }
            }
        }

        for (int i = 1; i < V + 1; i++) {
            if (graph[i][i] < min) {
                min = graph[i][i];
            }
        }

        System.out.println(min == 99999999 ? -1 : min);
    }


    static class Edge {
        int cost, vertex, start;

        public Edge(int cost, int vertex) {
            this.cost = cost;
            this.vertex = vertex;
        }

        public Edge(int cost, int vertex, int start) {
            this.cost = cost;
            this.vertex = vertex;
            this.start = start;
        }
    }

}
