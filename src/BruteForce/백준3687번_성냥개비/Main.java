package BruteForce.백준3687번_성냥개비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int t = Integer.parseInt(br.readLine());

            String min = findMin(t);
            String max = findMax(t);

            sb.append(min + " " + max);
            sb.append("\n");
        }

        System.out.println(sb.toString());

    }

    static String findMin(int num) {
        int eight = num / 7;
        int remain = num % 7;
        StringBuilder sb = new StringBuilder();

        while (eight > 0) {
            eight--;
            sb.append("8");
        }
        if (remain == 1) {
            sb.deleteCharAt(sb.length() - 1);
            sb.append("01");
        } else if (remain == 2) {
            sb.append("1");
        } else if (remain == 3) {
            if (sb.length() == 0) {
                sb.append("7");
            } else {
                if (sb.length() < 2) {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append("22");
                } else {
                    sb.deleteCharAt(sb.length() - 1);
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append("002");
                }

            }
        } else if (remain == 4) {
            if (sb.length() == 0) {
                sb.append("4");
            } else {
                sb.deleteCharAt(sb.length() - 1);
                sb.append("02");
            }
        } else if (remain == 5) {
            sb.append("2");
        } else if (remain == 6) {
            sb.append("6");
        }
        return sb.reverse().toString();
    }


    static String findMax(int num) {
        int one = num / 2;
        int remain = num % 2;
        StringBuilder sb = new StringBuilder();

        if (remain == 1) {
            one--;
            sb.append("7");
        }

        while (one > 0) {
            one--;
            sb.append("1");
        }
        return sb.toString();
    }
}
