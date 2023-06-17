package Greedy.프로그래머스Lv3단속카메라;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt((int[] o) -> o[1]));
        List<Integer> camera = new ArrayList<>(Arrays.asList(routes[0][1]));

        for(int i = 0; i < routes.length; i++) {
            if(routes[i][0] > camera.get(camera.size() - 1)) {
                camera.add(routes[i][1]);
            }
        }
        return camera.size();
    }
}