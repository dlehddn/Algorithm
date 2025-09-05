package StackQueue.백준9935번_문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Stack;

public class Third {
    static String inputString;
    static String bombString;
    static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        inputString = br.readLine();
        bombString = br.readLine();
        char lastBombChar = bombString.charAt(bombString.length() - 1);

        for (int i = 0; i < inputString.length(); i++) {
            stack.push(inputString.charAt(i));
            if (stack.peek() == lastBombChar && stack.size() >= bombString.length()) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < bombString.length(); j++) {
                    sb.append(stack.pop());
                }
                String target = sb.reverse().toString();
                if (!target.equals(bombString)) {
                    for (int j = 0; j < sb.length(); j++) {
                        stack.push(target.charAt(j));
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String result = sb.reverse().toString();
        System.out.println(result.length() == 0 ? "FRULA" : result);
    }
}
