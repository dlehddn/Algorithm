package BruteForce.프로그래머스Lv2_모음사전;

import java.util.*;
class Solution {
    public int solution(String word) {
        int answer = 0;
        int[] arr = word.chars()
                .mapToObj(c -> (char) c)
                .mapToInt(c -> {
                    switch (c) {
                        case 'A':
                            return 1;
                        case 'E':
                            return 2;
                        case 'I':
                            return 3;
                        case 'O':
                            return 4;
                        case 'U':
                            return 5;
                        default:
                            return 0;
                    }
                })
                .toArray();
        try {
            answer += arr.length;
            answer += recursion(arr, 0, 0);
            answer += recursion(arr, 0, 1);
            answer += recursion(arr, 0, 2);
            answer += recursion(arr, 0, 3);
            answer += recursion(arr, 0, 4);
        } catch (ArrayIndexOutOfBoundsException e) {}
        return answer;
    }

    private int recursion(int[] arr, int sum, int index) {
        if(arr[index] == 1) return sum;

        if(index == 0) sum += 781;
        if(index == 1) sum += 156;
        if(index == 2) sum += 31;
        if(index == 3) sum += 6;
        if(index == 4) sum += 1;
        arr[index]--;

        return recursion(arr, sum, index);
    }
}