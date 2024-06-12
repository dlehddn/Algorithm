package StackQueue.백준1725번_히스토그램;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    static int N, max;
    static int[] map;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        stack.push(0);

        for (int i = 1; i < N + 2; i++) {
            while (!stack.isEmpty()) {
                int peek = stack.peek();
                if (map[i] >= map[peek]) break;
                stack.pop();
                max = Math.max(max, map[peek] * (i - stack.peek() -1 ));
            }
            stack.push(i);
        }
        System.out.println(max);


    }

}
