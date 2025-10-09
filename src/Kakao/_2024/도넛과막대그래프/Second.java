package Kakao._2024.도넛과막대그래프;

// start 8:03
// end 8:25
// -> 22m 소요
import java.util.*;

class Second {
    int[] result = new int[4];
    int[] inDegree;
    List<Integer>[] graph;
    int INF = 0;

    public int[] solution(int[][] edges) {
        for (int[] e: edges) {
            int tmp = Math.max(e[0], e[1]);
            INF = Math.max(INF, tmp + 1);
        }
        graph = new List[INF];
        inDegree = new int[INF];

        for (int i = 1; i < INF; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e: edges) {
            inDegree[e[1]]++;
            graph[e[0]].add(e[1]);
        }

        for (int i = 1; i < INF; i++) {
            if (graph[i].size() >= 2 && inDegree[i] == 0) {
                result[0] = i;
            }
        }

        for (int startNode: graph[result[0]]) {
            int type = bfs(startNode);
            result[type]++;
        }

        return result;
    }

    private int bfs(int startNode) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[INF];
        q.add(startNode);

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            if (graph[poll].size() == 2 && inDegree[poll] >= 2) {
                return 3;
            }
            if (visited[poll]) {
                return 1;
            }
            visited[poll] = true;

            for (Integer next: graph[poll]) {
                q.add(next);
            }
        }
        return 2;
    }
}
