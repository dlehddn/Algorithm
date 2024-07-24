package DFSBFS.백준1976번_여행가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static int N, M;
    static int[] routes;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        routes = new int[M];
        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N + 1; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            routes[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M - 1; i++) {
            if (!bfs(routes[i], routes[i + 1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");

    }

    static boolean bfs(int start, int end) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            Integer poll = q.poll();

            if (poll == end) {
                return true;
            }

            for (Integer next : graph[poll]) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
        return false;
    }
}
