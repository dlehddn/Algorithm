package BruteForce.프로그래머스Lv2_카펫;

import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        ArrayList<Integer> list = makeDivisor(yellow);   // 약수를 담고있는 리스트 생성
        int N = list.size();
        for(int i = 0; i <= N - 1 - i; i++) {
            if((list.get(N-1-i)+2) * (list.get(i)+2) - yellow == brown) {
                answer[0] = list.get(N-1-i)+2;
                answer[1] = list.get(i)+2;
                break;
            }
        }
        return answer;
    }

    private ArrayList<Integer> makeDivisor(int number) {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= number; i++) {
            if(number % i == 0) {
                list.add(i);
            }
        }
        return list;
    }
}