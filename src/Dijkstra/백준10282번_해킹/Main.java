package Dijkstra.백준10282번_해킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T;
    static List<Node>[] graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph = new List[n + 1];
            for (int j = 1; j < n + 1; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph[from].add(new Node(to, cost));
            }

            boolean[] visited = new boolean[n + 1];
            PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(n1 -> n1.time));
            q.add(new Node(c, 0));
            int max = 0;
            int visitedCnt = 0;

            while (!q.isEmpty()) {
                Node poll = q.poll();

                if (visited[poll.target]) continue;
                visited[poll.target] = true;
                if (poll.time > max) max = poll.time;

                for (Node node : graph[poll.target]) {
                    if (!visited[node.target]) {
                        q.add(new Node(node.target, poll.time + node.time));
                    }
                }
            }

            for (boolean b : visited) {
                if(b) visitedCnt++;
            }

            sb.append(visitedCnt + " " + max + "\n");
        }
        System.out.println(sb);

    }

    static class Node {
        int target, time;

        public Node(int target, int time) {
            this.target = target;
            this.time = time;
        }
    }
}
