//import java.util.*;
//class Solution {
//    static ArrayList<Integer>[] graph;
//    static ArrayList<Integer>[] enterGraph;
//    static boolean[] enterCheck;
//    static int N;
//    static int start;
//    static int[] result;
//    public int[] solution(int[][] edges) {
//        N = Integer.MIN_VALUE;
//        for (int i = 0; i < edges.length; i++) {
//            for (int j = 0; j < 2; j++) {
//                if(edges[i][j] > N) N = edges[i][j];
//            }
//        }
//        graph = new ArrayList[N + 1];
//        enterGraph = new ArrayList[N + 1];
//        result = new int[4];
//        for (int i = 1; i < graph.length; i++) {
//            graph[i] = new ArrayList<>();
//            enterGraph[i] = new ArrayList<>();
//        }
//        for (int i = 0; i < edges.length; i++) {
//            int[] tmp = edges[i];
//            graph[tmp[0]].add(tmp[1]);
//            enterGraph[tmp[1]].add(tmp[0]);
//        }
//
//        findNode();
//        result[0] = start;
//        bfs();
//
//        return result;
//    }
//
//    static void findNode() {
//        for (int i = 1; i < graph.length; i++) {
//            if (graph[i].size() >= 2 && enterGraph[i].size() == 0) {
//                start = i;
//                return;
//            }
//        }
//    }
//
//    static void bfs() {
//
//        boolean[] visited = new boolean[N + 1];
//
//        for (Integer node : graph[start]) {
//            Queue<Integer> q = new LinkedList<>();
//            q.add(node);
//            int cnt = 0;
//            while (!q.isEmpty()) {
//                cnt++;
//                int poll = q.poll();
//                if (poll == node && cnt != 1) {
//                    result[1] += 1;
//                    break;
//                }
//                if(visited[poll]) continue;
//                visited[poll] = true;
//                if (graph[poll].size() >= 2) {
//                    result[3] += 1;
//                    break;
//                } else if (graph[poll].size() == 0) {
//                    result[2] += 1;
//                    break;
//                }
//                for (Integer next : graph[poll]) {
//                        q.add(next);
//                }
//            }
//        }
//    }
//}