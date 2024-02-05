package Kakao._2024.q2;
/**
 * 1. 생성한 정점 찾기 : 나가는 방향 2개 이상 + 들어오는건 하나도 없으면 생성한 정점
 *
 * 2. 어떤 모양 그래프인지 찾기
 * 도넛모양 : 노드 수 == 간선 수
 * 막대모양 : 노드 수 - 1 == 간선 수
 * 8자모양 : 노드 수 + 1 == 간선 수
 *
 *
 * debug : 생성한 정점 찾는 부분에서 실수, 단순히 내가 뻗어나간 애한테 다시 들어오는게 아니라 다른 쪽에서도 나한테
 * 들어올 수 있음.
 */
import java.util.*;

class Solution {
    static List<Integer>[] graph;
    static int[] reverseGraph;
    static int[] result;
    static int N;

    public int[] solution(int[][] edges) {
        N = Arrays.stream(edges)
                .flatMapToInt(arr -> Arrays.stream(arr))
                .max()
                .orElse(-1);

        graph = new ArrayList[N + 1];
        reverseGraph = new int[N + 1];
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
        boolean[] visited = new boolean[N + 1];
        int vertex = 0;
        int edge = 0;
        q.add(n);
        visited[n] = true;

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            vertex++;

            for (Integer node : graph[poll]) {
                edge++;
                if (!visited[node]) {
                    visited[node] = true;
                    q.add(node);
                }
            }
        }

        if (vertex == edge) {
            result[1]++;
        } else if (vertex - 1 == edge) {
            result[2]++;
        } else {
            result[3]++;
        }

    }
}

