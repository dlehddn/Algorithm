package Kakao._2022.등산코스정하기;

import java.util.*;
class FirstSolve {

    static ArrayList<Node>[] graph;
    static int[] dist;
    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static boolean[] visited;
    static HashSet<Integer> summitsSet = new HashSet<>();
    static ArrayList<Node> result = new ArrayList<>();

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        graph = new ArrayList[n + 1];
        dist = new int[n + 1];
        visited = new boolean[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < paths.length; i++) {
            int[] p = paths[i];
            graph[p[0]].add(new Node(p[1], p[2]));
            graph[p[1]].add(new Node(p[0], p[2]));
        }
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < summits.length; i++) {
            summitsSet.add(summits[i]);
        }

        dijkstra(n, gates, summits);
        Collections.sort(result);
        // Test //


        //////////

        return new int[]{result.get(0).nodeNum, result.get(0).score};
    }

    static void dijkstra(int n, int[] gates, int[] summits) {
        for (int i = 0; i < gates.length; i++) {
            pq.add(new Node(gates[i], 0));
            dist[gates[i]] = 0;
        }

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if(visited[poll.nodeNum]) continue;
            visited[poll.nodeNum] = true;
            if(summitsSet.contains(poll.nodeNum)) continue;

            for (Node node : graph[poll.nodeNum]) {
                int min = Math.min(dist[node.nodeNum], Math.max(dist[poll.nodeNum], node.score));
                dist[node.nodeNum] = min;
                pq.add(new Node(node.nodeNum, min));
                if (summitsSet.contains(node.nodeNum)) {
                    result.add(new Node(node.nodeNum, min));
                }
            }
        }

    }

    static class Node implements Comparable<Node>{
        int nodeNum;
        int score;

        public Node(int nodeNum, int score) {
            this.nodeNum = nodeNum;
            this.score = score;
        }

        @Override
        public int compareTo(Node o) {
            if (this.score != o.score) {
                return this.score - o.score;
            } else {
                return this.nodeNum - o.nodeNum;
            }
        }
    }
}