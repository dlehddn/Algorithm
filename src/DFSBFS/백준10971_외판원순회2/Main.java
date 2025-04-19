package DFSBFS.백준10971_외판원순회2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, min;
    static List<Node>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        min = Integer.MAX_VALUE;
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int cost = Integer.parseInt(st.nextToken());
                if (cost != 0) {
                    graph[i].add(new Node(j, cost));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            dfs(i, i, 0, 0, visited);
        }
        System.out.println(min);
    }

    static void dfs(int start, int curNum, int cnt, int cost, boolean[] visited) {
        for (Node next : graph[curNum]) {
            if (!visited[next.num]) {
                visited[next.num] = true;
                dfs(start, next.num, cnt + 1, cost + next.cost, visited);
                visited[next.num] = false;
            }
            if (next.num == start && cnt == N - 1) {
                min = Math.min(min, cost + next.cost);
            }
        }
    }

    static class Node {
        int num, cost, cnt;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        public Node(int num, int cost, int cnt) {
            this.num = num;
            this.cost = cost;
            this.cnt = cnt;
        }
    }
}
