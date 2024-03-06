package Kakao._2022.등산코스정하기;

import java.util.*;

class Solution {

    static int[] dist, result;
    static boolean[] visited;
    static List<Node>[] graph;

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        dist = new int[n + 1];
        visited = new boolean[n + 1];
        graph = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            graph[path[0]].add(new Node(path[1], path[2]));
            graph[path[1]].add(new Node(path[0], path[2]));
        }

        result = new int[]{0, Integer.MAX_VALUE};
        dijkstra(gates, summits);
        return result;
    }

    static void dijkstra(int[] gates, int[] summits) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        for (int enter : gates) {
            for (Node node : graph[enter]) {
                pq.add(node);
            }
        }

        while (!pq.isEmpty()) {
            Node poll = pq.poll();

            if(visited[poll.vertex]) continue;
            visited[poll.vertex] = true;
            dist[poll.vertex] = poll.cost;
            if(Arrays.stream(summits).anyMatch(s -> s == poll.vertex)) {
                if (result[1] > poll.cost) {
                    result[0] = poll.vertex;
                    result[1] = poll.cost;
                } else if (result[1] == poll.cost && result[0] > poll.vertex) {
                    result[0] = poll.vertex;
                }
                continue;
            }
            for (Node node : graph[poll.vertex]) {
                int nextCost = Math.max(poll.cost, node.cost);
                pq.add(new Node(node.vertex, nextCost));
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