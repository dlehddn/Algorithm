package Kakao._2024.q2;
/**
 * 1. 생성한 정점 찾기 : 나가는 방향 2개 이상 + 들어오는건 하나도 없으면 생성한 정점
 * <p>
 * 2. 어떤 모양 그래프인지 찾기
 * 도넛모양 : 루프가 생겨서 다시 돌아옴
 * 막대모양 : 루프가 안생김
 * 8자모양 : 루프가 생김 + 유일하게 임의의 노드에서 간선 2개를 가짐
 */

import java.util.*;

class Solution2 {
    static List<Integer>[] graph;
    static int[] reverseGraph;
    static boolean[] visited;
    static int[] result;
    static int N;

    public int[] solution(int[][] edges) {
        N = Arrays.stream(edges)
                .flatMapToInt(arr -> Arrays.stream(arr))
                .max()
                .orElse(-1);

        graph = new ArrayList[N + 1];
        reverseGraph = new int[N + 1];
        visited = new boolean[N + 1];
        result = new int[4];

        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        Arrays.stream(edges)
                .forEach(arr -> {
                    graph[arr[0]].add(arr[1]);
                    reverseGraph[arr[1]]++;
                });

        // start node 찾기
        for (int i = 1; i < N + 1; i++) {
            if (graph[i].size() > 2) {
                result[0] = i;
                break;
            } else if (graph[i].size() == 2 && reverseGraph[i] == 0) {
                result[0] = i;
                break;
            }
        }

        for (Integer n : graph[result[0]]) {
            findType(n);
        }

        return result;
    }

    private void findType(Integer n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(n);
        while (!q.isEmpty()) {
            Integer poll = q.poll();
            if (visited[poll]) continue;
            visited[poll] = true;

            if (graph[poll].size() == 2) {
                result[3]++;
                break;
            }
            if (graph[poll].size() == 0) {
                result[2]++;
                break;
            }
            if (graph[poll].get(0).equals(n)) {
                result[1]++;
                break;
            }
            q.add(graph[poll].get(0));
        }
    }
}

