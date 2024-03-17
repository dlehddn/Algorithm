package Dijkstra.백준13549번_숨바꼭질3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        boolean[] visited = new boolean[100001];
        q.add(new Node(N, 0));
        visited[N] = true;

        while (true) {
            Node poll = q.poll();
            visited[poll.node] = true;

            if (poll.node == K) {
                System.out.println(poll.cost);
                return;
            }

            if (poll.node * 2 <= 100000 && !visited[poll.node * 2]) {
                q.add(new Node(poll.node * 2, poll.cost));
            }
            if (poll.node - 1 >= 0 && !visited[poll.node - 1] ) {
                q.add(new Node(poll.node - 1, poll.cost + 1));
            }
            if (poll.node + 1 <= 100000 && !visited[poll.node + 1]) {
                q.add(new Node(poll.node + 1, poll.cost + 1));
            }
        }
    }

    static class Node {
        int node, cost;

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
    }

}
