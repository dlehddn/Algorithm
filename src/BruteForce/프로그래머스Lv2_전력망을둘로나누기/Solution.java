package BruteForce.프로그래머스Lv2_전력망을둘로나누기;
import java.util.*;

class Solution {


    public int solution(int n, int[][] wires) {
        boolean[] visited = new boolean[n + 1];
        int count = 0;
        int min = 98;
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < wires.length; i++) {
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }                                                   // 입력배열을 트리로 만들기(인접 리스트 생성)

        for(int i = 0; i < wires.length; i++) {
            int from = wires[i][0];
            int to = wires[i][1];

            list.get(from).remove(list.get(from).indexOf(to));
            list.get(to).remove(list.get(to).indexOf(from)); // 와이어 연결 끊기

            Arrays.fill(visited, false);
            int tempF = dfs(list, visited, from, count);
            count = 0;                                      // 1번 서브넷 송전탑 개수 구하기

            Arrays.fill(visited, false);
            int tempT = dfs(list, visited, to, count);
            count = 0;                                      // 2번 서브넷 송전탑 개수 구하기

            int result = Math.abs(tempF-tempT);
            if(result < min) min = result;                  // 개수차이가 이전보다 작으면 최소값 갱신

            list.get(from).add(to);
            list.get(to).add(from);   // 선 복구
        }
        return min;
    }


    private int dfs(ArrayList<ArrayList<Integer>> list, boolean[] visited, int nodeNumber, int count) {
        count++;
        visited[nodeNumber] = true;    // 방문처리

        for (int node : list.get(nodeNumber)) {
            if(!visited[node]) {
                count = dfs(list, visited, node, count);
            }
        }
        return count;
    }
}