package StackQueue.프로그래머스Lv1_같은숫자는싫어;

import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        int previous = -1;
        for(int present : arr) {
            if(present != previous) result.add(present);
            previous = present;
        }

        return result.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}