package BruteForce.프로그래머스Lv1_모의고사;

import java.util.*;
class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] first = {1,2,3,4,5};   // 크기5
        int[] second = {2,1,2,3,2,4,2,5};   // 8
        int[] third = {3,3,1,1,2,2,4,4,5,5};  // 10
        int num_1 = 0, num_2 = 0, num_3 = 0;

        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == first[i % 5]) {
                num_1++;
            }
        }
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == second[i % 8]) {
                num_2++;
            }
        }
        for(int i = 0; i < answers.length; i++) {
            if(answers[i] == third[i % 10]) {
                num_3++;
            }
        }
        int max = num_1;
        if(num_2 > max) max = num_2;
        if(num_3 > max) max = num_3;

        if(num_1 == max) {
            list.add(1);
        }
        if(num_2 == max) {
            list.add(2);
        }
        if(num_3 == max) {
            list.add(3);
        }
        int[] arr = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
