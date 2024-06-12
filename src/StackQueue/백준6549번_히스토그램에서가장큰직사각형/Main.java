package StackQueue.백준6549번_히스토그램에서가장큰직사각형;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] map;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) return;
            map = new int[N + 2];
            stack = new Stack<>();

            for (int i = 1; i <= N; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }

            stack.push(0);

            long answer = 0;
            for (int i = 1; i < N + 2; i++) {
                while (!stack.isEmpty()) {
                    int peek = stack.peek();
                    if (map[i] >= map[peek]) break;
                    else {
                        stack.pop();
                        long tmp = (long) map[peek] * (i - stack.peek() - 1);
                        answer = Math.max(answer, tmp);
                    }
                }
                stack.push(i);
            }
            System.out.println(answer);
        }
    }
}
