package Greedy.백준2812번_크게만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static Deque<Integer> dq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        dq = new ArrayDeque<>();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String nums = br.readLine();

        for (int i = 0; i < nums.length(); i++) {
            while (true) {
                if (dq.size() == 0) {
                    dq.push(nums.charAt(i) - '0');
                    break;
                }

                if (dq.peek() < nums.charAt(i) - '0' && K > 0) {
                    dq.pop();
                    K--;
                } else {
                    dq.push(nums.charAt(i) - '0');
                    break;
                }
            }
        }

        for (int i = 1; i <= K; i++) {
            dq.pop();
        }

        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            sb.append(dq.removeLast());
        }

        System.out.println(sb.toString());
    }
}
