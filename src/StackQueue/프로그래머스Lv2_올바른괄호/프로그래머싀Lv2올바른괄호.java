package StackQueue.프로그래머스Lv2_올바른괄호;
import java.util.*;
class Solution {
    boolean solution(String s) {
        Stack<Character> stack = new Stack<Character>();

        for(int i = s.length() - 1; i >= 0; i--) {
            stack.push(s.charAt(i));
        }

        int count = 0;
        while(!stack.empty()) {
            char c = stack.pop();
            if(c == '(') {
                count++;
            }
            if(c == ')') {
                count--;
            }
            if(count < 0) return false;
        }
        if(count == 0) return true;
        else return false;

    }
}