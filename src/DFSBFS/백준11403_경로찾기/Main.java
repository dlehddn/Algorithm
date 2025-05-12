package DFSBFS.백준11403_경로찾기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    graph[i].add(j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (isConnected(i, j)) {
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static boolean isConnected(int from, int to) {
        boolean[] visited = new boolean[N];
        visited[from] = true;
        Queue<Integer> q = new ArrayDeque<>();
        q.add(from);

        while (!q.isEmpty()) {
            Integer poll = q.poll();

            for (Integer next : graph[poll]) {
                if (next == to) {
                    return true;
                }
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
        return false;
    }

}
