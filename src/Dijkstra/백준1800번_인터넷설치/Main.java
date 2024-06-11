package Dijkstra.백준1800번_인터넷설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Edge>[] graph;
    static int N, P, K, start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        start = 0;
        end = Integer.MIN_VALUE;

        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[s].add(new Edge(e, cost));
            graph[e].add(new Edge(s, cost));
            end = Math.max(end, cost);
        }

        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int answer = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (bfs(mid)) {
                end = mid - 1;
                answer = mid;
            } else {
                start = mid + 1;
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    static boolean bfs(int mid) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        boolean[] visited = new boolean[N + 1];
        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge poll = pq.poll();

            if (visited[poll.vertex] || poll.cost > K) continue;
            visited[poll.vertex] = true;

            if (poll.vertex == N) return true;

            for (Edge edge : graph[poll.vertex]) {
                if (edge.cost <= mid) {
                    pq.add(new Edge(edge.vertex, poll.cost));
                } else {
                    pq.add(new Edge(edge.vertex, poll.cost + 1));
                }
            }
        }
        return false;
    }

    static class Edge {
        int vertex, cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
