package DFSBFS.백준1976번_여행가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main3 {

    static int N, M;
    static List<Integer>[] graph;
    static int[] plan;
    static String result = "YES";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        plan = new int[M];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }
        // 세팅 끝

        for (int i = 0; i < M - 1; i++) {
            if (!canGo(plan[i], plan[i + 1])) {
                result = "NO";
                break;
            }
        }
        System.out.println(result);
    }

    static boolean canGo(int start, int end) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int poll = q.poll();

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
