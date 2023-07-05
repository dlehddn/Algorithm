package BfsDfs.프로그래머스Lv3_가장먼노드;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] edge) {
        boolean[] visited = new boolean[n + 1];
        List<Integer>[] node = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) node[i] = new ArrayList<>();
        // 리스트형 배열생성 -> 리스트 초기화

        for(int i = 0; i < edge.length; i++) {
            node[edge[i][0]].add(edge[i][1]);
            node[edge[i][1]].add(edge[i][0]);
        }
        // 인접 리스트 생성

        int[] numOfEdge = bfs(1, node, visited);
        // 정점 별, 거쳐온 간선의 개수를 담은 배열 생성

        int answer = countNumOfMax(numOfEdge);
        // 가장 먼 노드가 몇개인지 세는 과정

        return answer;


    }

    private int[] bfs(int state, List<Integer>[] node, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.add(state);
        visited[state] = true;
        int[] numOfEdge = new int[node.length];
        while(!q.isEmpty()) {
            state = q.poll();
            for(int a : node[state]) {
                if(!visited[a]) {
                    numOfEdge[a] = numOfEdge[state] + 1;
                    visited[a] = true;
                    q.add(a);
                }
            }
        }
        return numOfEdge;
    }

    private int countNumOfMax(int[] numOfEdge) {
        int maxValue = 0;
        int count = 1;
        for(int num : numOfEdge) {
            if(num > maxValue) {
                maxValue = num;
                count = 1;
            }
            else if(num == maxValue) {
                count++;
            }
        }
        return count;
    }

}