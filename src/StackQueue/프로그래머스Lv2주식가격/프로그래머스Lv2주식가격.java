package StackQueue.프로그래머스Lv2주식가격;

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int[] answer = new int[prices.length];

        for(int i = 0; i < prices.length; i++) {
            while(!stack.empty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }   // 계속 가격이 줄지 않으면 빈 배열로 남게된다. 아래를 추가하자.

        while(!stack.empty()) {
            answer[stack.peek()] = prices.length - 1 - stack.peek(); // 배열의 가장 끝자리부터 하나씩 안쪽으로 들어가면서 처리해주는 과정
            stack.pop();
        }
        return answer;
    }
}
