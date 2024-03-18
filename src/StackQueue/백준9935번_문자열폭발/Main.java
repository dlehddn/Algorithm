package StackQueue.백준9935번_문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    static Stack<Character> stack;
    static String init, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init = br.readLine();
        target = br.readLine();
        stack = new Stack<>();

        int cnt = 0;
        while (cnt < init.length()) {
            stack.push(init.charAt(cnt));

            boolean flag = false;
            if (stack.size() >= target.length()) {
                for (int i = 0; i < target.length(); i++) {
                    if (stack.get(stack.size() - i - 1) == target.charAt(target.length() - i - 1)) {
                        if(i == target.length() - 1) flag = true;
                    } else {
                        break;
                    }
                }
            }
            if (flag) {
                for (int i = 0; i < target.length(); i++) {
                    stack.pop();
                }
            }
            cnt++;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb.length() != 0 ? sb.reverse().toString() : "FRULA");
    }
}
