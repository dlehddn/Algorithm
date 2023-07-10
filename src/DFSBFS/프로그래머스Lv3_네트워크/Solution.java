package DFSBFS.프로그래머스Lv3_네트워크;

import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < computers.length; i++) {
            for(int j = 0; j < computers[i].length; j++) {
                if(computers[i][j] == 1 && i != j) {
                    graph[i].add(j);
                }
            }
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(i, graph, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(int nodeNumber, ArrayList<Integer>[] graph, boolean[] visited) {
        visited[nodeNumber] = true;
        for(int node : graph[nodeNumber]) {
            if(!visited[node]) dfs(node, graph, visited);
        }
    }
}