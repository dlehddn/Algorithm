package Recursion.프로그래머스Lv2_하노이의탑;

import java.util.*;

class Solution {

    ArrayList<int[]> list = new ArrayList<>();

    public int[][] solution(int n) {
        move(n, 1, 3, 2);
        return list.toArray(new int[list.size()][2]);
    }

    private void move(int n, int start, int end, int except) {
        if(n == 1) {
            list.add(new int[]{start, end});
            return;
        }
        else {
            move(n-1, start, except, end);
            move(1, start, end, except);
            move(n-1, except, end, start);
        }
    }

}