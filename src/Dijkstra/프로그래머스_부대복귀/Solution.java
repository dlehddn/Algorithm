package Dijkstra.프로그래머스_부대복귀;

import java.util.*;

class Solution {
    private static final int INF = 200_000;
    private int[] dist;
    private List<Integer>[] graph;

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        dist = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            dist[i] = INF;
        }
        graph = new List[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] road: roads) {
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        dijkstra(destination, new boolean[n + 1]);
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]] == INF ? -1 : dist[sources[i]];
        }

        return answer;
    }

    private void dijkstra(int start, boolean[] visited) {
        PriorityQueue<Next> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.add(new Next(start, 0));

        while (!pq.isEmpty()) {
            Next poll = pq.poll();
            if (visited[poll.num]) {
                continue;
            }
            visited[poll.num] = true;
            dist[poll.num] = poll.cost;

            for (int nextNum: graph[poll.num]) {
                pq.add(new Next(nextNum, poll.cost + 1));
            }
        }
    }

    static class Next {
        int num, cost;

        public Next(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
