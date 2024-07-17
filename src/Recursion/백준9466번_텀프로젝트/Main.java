package Recursion.백준9466번_텀프로젝트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int T, N, cnt;
    static int[] graph;
    static boolean[] visited, finished;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            graph = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            cnt = 0;
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[j] = Integer.parseInt(st.nextToken());
            }
            bfs();
            sb.append(N - cnt  + "\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        for (int i = 1; i <= N; i++) {
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            visited[i] = true;

            List<Integer> list = new ArrayList<>();

            while (!q.isEmpty()) {
                int poll = q.poll();
                list.add(poll);
                if (visited[graph[poll]] && !finished[graph[poll]]) {
                    cnt++;
                    for (int j = graph[poll]; j != poll; j = graph[j]) {
                        cnt++;
                    }
                } else if (!visited[graph[poll]]) {
                    q.add(graph[poll]);
                    visited[graph[poll]] = true;
                }
            }

            for (Integer n : list) {
                finished[n] = true;
            }

        }
    }

}
