package TwoPointer.백준5430번_AC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int left, right, T, N;
    static int[] arr;
    static String command;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        loop:
        for (int i = 0; i < T; i++) {
            int flag = -1;
            command = br.readLine();
            N = Integer.parseInt(br.readLine());
            arr = new int[N];
            makeArr(br.readLine());
            left = 0;
            right = arr.length - 1;
            for (int j = 0; j < command.length(); j++) {
                if (command.charAt(j) == 'R') {
                    flag *= -1;
                } else {
                    if (left > right || N == 0) {
                        sb.append("error" + "\n");
                        continue loop;
                    }
                    if (flag == -1) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
            makeAnswer(flag);
        }
        System.out.println(sb);
    }

    static void makeAnswer(int flag) {
        StringBuilder tmp = new StringBuilder();
        tmp.append("[");
        if (N != 0) {
            if (flag == -1) {
                for (int i = left; i <= right; i++) {
                    tmp.append(arr[i]);
                    if (i != right) {
                        tmp.append(",");
                    }
                }
            } else {
                for (int i = right; i >= left; i--) {
                    tmp.append(arr[i]);
                    if (i != left) {
                        tmp.append(",");
                    }
                }
            }
        }
        tmp.append("]");
        sb.append(tmp).append("\n");
    }

    static void makeArr(String origin) {
        if (N == 0) return;
        String center = origin.substring(1, origin.length() - 1);
        arr = Arrays.stream(center.split(","))
                .mapToInt(n -> Integer.parseInt(n))
                .toArray();
    }
}
