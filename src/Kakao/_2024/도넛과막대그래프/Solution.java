package Kakao._2024.도넛과막대그래프;

// 10:22 start
// 10:56 end
// -> 34m 소요
import java.util.*;
// 1. 주어진 간선 정보로 임의의 정점 k를 찾아내야함.
// -> 임의의 정점 k는 오직 나가는 간선만 존재하며 나가는 간선의 수는 2 이상이다.
// 2. k를 찾았으면, k에서 나가는 간선을 시작으로 각 그래프 그룹이 어떤 모양인지 체크 가능해야하다.
// -> (1): 그래프를 순회하는 동안 밖으로 나가는 간선이 없는 노드를 만난 경우 -> 막대
// -> (2): 그래프를 순회하는 동안 2개의 in, 2개의 out을 가지는 노드를 만난 경우 -> 8자
// -> (3): 그 외 경우로 순회가 끝난 경우 -> 도넛
class Solution {
    int INF = Integer.MIN_VALUE;
    List<Integer>[] graph;
    int[] indegree;

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        for (int[] edge: edges) {
            int max = Math.max(edge[0], edge[1]);
            INF = Math.max(INF, max);
        }
        graph = new List[INF + 1];
        indegree = new int[INF + 1];

        for (int i = 1; i <= INF; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge: edges) {
            graph[edge[0]].add(edge[1]);
            indegree[edge[1]]++;
        }
        for (int i = 1; i <= INF; i++) {
            if (graph[i].size() >= 2 && indegree[i] == 0) {
                answer[0] = i;
                break;
            }
        }
        for (int next: graph[answer[0]]) {
            int type = search(next);
            answer[type]++;
        }
        return answer;
    }

    private int search(int node) {
        Queue<Integer> q = new ArrayDeque();
        boolean[] visited = new boolean[INF + 1];
        q.add(node);
        visited[node] = true;

        while(!q.isEmpty()) {
            Integer poll = q.poll();

            if (graph[poll].size() == 0) {
                return 2;
            }
            if (graph[poll].size() == 2 && indegree[poll] >= 2) {
                return 3;
            }

            for(int next: graph[poll]) {
                if (!visited[next]) {
                    q.add(next);
                    visited[next] = true;
                }
            }
        }
        return 1;
    }
}
