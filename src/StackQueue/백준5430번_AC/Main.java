package StackQueue.백준5430번_AC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main {

    static int T, N;
    static String command;
    static Deque<Integer> dq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        loop:
        for (int i = 0; i < T; i++) {
            command = br.readLine();
            N = Integer.parseInt(br.readLine());
            dq = new ArrayDeque<>();
            String nums = br.readLine();
            String substring = nums.substring(1, nums.length() - 1);
            int flag = 0;
//            for (int j = 1; j <= N; j++) {
//                dq.addLast(nums.charAt(2 * j - 1) - '0');
//            }
            String[] split = substring.split(",");
            if (substring.length() > 0) {
                for (String s : split) {
                    dq.addLast(Integer.parseInt(s));
                }
            }
            for (int j = 0; j < command.length(); j++) {
                if (command.charAt(j) == 'R') {
                    flag = Math.abs(flag - 1);
                } else {
                    try {
                        if (flag == 0) {
                            dq.pop();
                        } else {
                            dq.removeLast();
                        }
                    } catch (Exception e) {
                        sb.append("error" + "\n");
                        continue loop;
                    }
                }
            }

            sb.append("[");
            if (flag == 0) {
                for (Integer n : dq) {
                    sb.append(n + ",");
                }
            } else {
                Iterator<Integer> iterator = dq.descendingIterator();
                while (iterator.hasNext()) {
                    sb.append(iterator.next() + ",");
                }
            }
            if (dq.size() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append("]" + "\n");
        }
        System.out.println(sb);
    }
}
