package TopologicalSort.백준1516번_게임개발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
    static List<Integer>[] map;
    static int[] times, indegree, result;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new List[N + 1];
        times = new int[N + 1];
        indegree = new int[N + 1];
        result = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            map[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());

            int n = Integer.parseInt(st.nextToken());
            while (n != -1) {
                map[n].add(i);
                indegree[i]++;
                n = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(new Node(i, times[i]));
            }
        }


        while (!q.isEmpty()) {
            Node poll = q.poll();
            result[poll.vertex] = poll.cost;

            for (Integer next : map[poll.vertex]) {
                if (--indegree[next] == 0) {
                    q.add(new Node(next, poll.cost + times[next]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i] + "\n");
        }

        System.out.println(sb);

    }

    static class Node {
        int vertex, cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
