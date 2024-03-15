package TopologicalSort.백준2252번_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static List<Integer>[] graph;
    static Queue<Integer> q;
    static int[] table;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        table = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            table[to]++;
        }

        q = new LinkedList<>();
        for (int i = 1; i < N + 1; i++) {
            if (table[i] == 0) {
                q.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int poll = q.poll();
            sb.append(poll);
            sb.append(" ");

            for (Integer target : graph[poll]) {
                if (table[target] > 0) {
                    table[target]--;
                    if (table[target] == 0) {
                        q.add(target);
                    }
                }
            }
        }
        System.out.println(sb);
    }
}
