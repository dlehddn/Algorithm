package Kakao._2021.합승택시요금;

// start 10:30
// end 10:56
// -> 26m 소요

// max node = 200
// max edge = 20000
// dijkstra O(n) = 20000 * log(200) ~= 160,000
import java.util.*;
class Solution {
    List<Edge>[] graph;
    int[] distA;
    int[] distB;
    int[] distS;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        graph = new List[n + 1];
        distA = new int[n + 1];
        distB = new int[n + 1];
        distS = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            distA[i] = 70000000;
            distB[i] = 70000000;
            distS[i] = 70000000;
        }

        for (int[] fare: fares) {
            graph[fare[0]].add(new Edge(fare[1], fare[2]));
            graph[fare[1]].add(new Edge(fare[0], fare[2]));
        }
        dijkstra(s, distS, n);
        dijkstra(a, distA, n);
        dijkstra(b, distB, n);

        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, distS[i] + distA[i] + distB[i]);
        }

        return answer;
    }

    private void dijkstra(int start, int[] dist, int n) {
        PriorityQueue<Edge> pq =
            new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        boolean[] visited = new boolean[n + 1];
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge poll = pq.poll();
            if (visited[poll.vertex]) {
                continue;
            }
            visited[poll.vertex] = true;

            dist[poll.vertex] = Math.min(dist[poll.vertex], poll.cost);
            for (Edge next: graph[poll.vertex]) {
                pq.add(new Edge(next.vertex, poll.cost + next.cost));
            }
        }
    }

    static class Edge {
        int vertex;
        int cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
