package TopologicalSort.백준2637번_장난감조립;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Node>[] reverseGraph;
    static int[] inDegree, outDegree, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        reverseGraph = new ArrayList[N + 1];
        inDegree = new int[N + 1];
        outDegree = new int[N + 1];
        sum = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            reverseGraph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            reverseGraph[from].add(new Node(to, cost));
            inDegree[from]++;
            outDegree[to]++;
        }
        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                sb.append(i + " " + sum[i]);
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(N, 1));

        while (!q.isEmpty()) {
            Node poll = q.poll();

            for (Node node : reverseGraph[poll.target]) {
                if (outDegree[node.target] > 0) {
                    outDegree[node.target]--;
                    sum[node.target] += node.cost * poll.cost;
                }
                if (outDegree[node.target] == 0) {
                    if (inDegree[node.target] != 0) {
                        q.add(new Node(node.target, sum[node.target]));
                    }
                }
            }
        }
    }

    static class Node {
        int target, cost;

        public Node(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }
}
