package StackQueue.백준17298번_오큰수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, answer;
    static int N;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        answer = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        stack.push(0);

        for (int i = 1; i < N; i++) {
            if (arr[i] <= arr[i - 1]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                    int pop = stack.pop();
                    answer[pop] = arr[i];
                }
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : answer) {
            sb.append(i + " ");
        }

        System.out.println(sb);

    }
}
