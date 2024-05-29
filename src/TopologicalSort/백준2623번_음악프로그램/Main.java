package TopologicalSort.백준2623번_음악프로그램;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Queue<Integer> q = new LinkedList<>();
    static List<Integer>[] map;
    static int[] indegree, result;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new List[N + 1];
        indegree = new int[N + 1];
        result = new int[N];

        for (int i = 1; i < N + 1; i++) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int prev = 0;
            for (int j = 0; j < cnt; j++) {
                int next = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    prev = next;
                    continue;
                }
                indegree[next]++;
                map[prev].add(next);
                prev = next;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int pointer = 0;
        while (!q.isEmpty()) {
            int poll = q.poll();
            result[pointer++] = poll;

            for (Integer next : map[poll]) {
                if (--indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            if (i == 0) {
                System.out.println(0);
                return;
            }
            sb.append(i + "\n");
        }

        System.out.println(sb);
    }
}
