package DFSBFS.백준9205번_맥주마시면서걸어가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, N;
    static List<Node>[] graph;
    static Node[] init;
    static String result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            graph = new List[N + 2];
            init = new Node[N + 2];
            for (int j = 0; j < N + 2; j++) {
                graph[j] = new ArrayList<>();
            }
            StringTokenizer st;
            for (int j = 0; j < N + 2; j++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                init[j] = new Node(y, x, j);
                for (int k = 0; k < N + 2; k++) {
                    if (j != k) {
                        graph[k].add(new Node(y, x, j));
                    }
                }
            }
            result = "sad";
            bfs();
            System.out.println(result);

        }

    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 2];
        q.add(init[0]);
        visited[0] = true;


        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (poll.y == init[N + 1].y && poll.x == init[N + 1].x) {
                result = "happy";
                return;
            }

            for (Node node : graph[poll.num]) {
                if (Math.abs(node.y - poll.y) + Math.abs(node.x - poll.x) <= 1000
                && !visited[node.num]) {
                    q.add(node);
                    visited[node.num] = true;
                }
            }
        }
    }

    static class Node {
        int y, x, num;

        public Node(int y, int x, int num) {
            this.y = y;
            this.x = x;
            this.num = num;
        }
    }

}
