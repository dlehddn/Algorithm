package DFSBFS.프로그래머스Lv3_순위;

import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        ArrayList<Integer>[] posGraph = makePositiveGraph(n, results);
        ArrayList<Integer>[] negGraph = makeNegativeGraph(n, results);
        // for(ArrayList<Integer> a : negGraph) System.out.println(a);

        int answer = 0;
        for(int i = 0; i < n + 1; i++) {
            HashSet<Integer> posSet = bfs(i, posGraph);
            HashSet<Integer> negSet = bfs(i, negGraph);

            HashSet<Integer> allSet = new HashSet<>(posSet);
            allSet.addAll(negSet);

            if(allSet.size() == n-1) answer++;
        }

        // for(int a : negSet) System.out.println(a);

        return answer;

    }

    private ArrayList<Integer>[] makePositiveGraph(int n, int[][] results) {
        ArrayList<Integer>[] posGraph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) posGraph[i] = new ArrayList<>();
        for(int i = 0; i < results.length; i++) posGraph[results[i][0]].add(results[i][1]);
        return posGraph;
    }

    private ArrayList<Integer>[] makeNegativeGraph(int n, int[][] results) {
        ArrayList<Integer>[] negGraph = new ArrayList[n + 1];
        for(int i = 0; i < n + 1; i++) negGraph[i] = new ArrayList<>();
        for(int i = 0; i < results.length; i++) negGraph[results[i][1]].add(results[i][0]);
        return negGraph;
    }

    private HashSet<Integer> bfs(int node, ArrayList<Integer>[] Graph) {
        boolean[] visited = new boolean[Graph.length];
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        q.add(node);
        visited[node] = true;


        while(!q.isEmpty()) {
            node = q.poll();
            for(int a : Graph[node]) {
                if(!visited[a]) {
                    visited[a] = true;
                    q.add(a);
                    set.add(a);
                }
            }
        }
        return set;
    }
}