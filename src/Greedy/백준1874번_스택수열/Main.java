package Greedy.백준1874번_스택수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {

    static Stack<Integer> stack = new Stack<>();
    static Queue<Integer> q1 = new LinkedList<>();
    static Queue<Integer> q2 = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {
            q1.add(i);
        }

        for (int i = 0; i < N; i++) {
            q2.add(Integer.parseInt(br.readLine()));
        }

        while (true) {
            int target = q2.poll();
            while (stack.isEmpty() || stack.peek() != target) {
                if (q1.isEmpty()) {
                    System.out.println("NO");
                    return;
                }
                stack.push(q1.poll());
                sb.append("+" + "\n");
            }

            stack.pop();
            sb.append("-" + "\n");

            if (q1.isEmpty() && stack.isEmpty()) break;
        }

        System.out.println(sb);

    }
}
