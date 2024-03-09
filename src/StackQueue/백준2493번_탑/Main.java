package StackQueue.백준2493번_탑;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] towers, result;
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        towers = new int[N];
        result = new int[N];
        stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            towers[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) {
            while (true) {
                if (!stack.isEmpty()) {
                    Integer peek = stack.peek();
                    if (towers[peek] < towers[i]) {
                        stack.pop();
                        result[peek] = i + 1;
                    } else {
                        break;
                    }
                } else break;
            }
            stack.push(i);
        }

        for (int i : result) {
            System.out.print(i + " ");
        }


    }
}
