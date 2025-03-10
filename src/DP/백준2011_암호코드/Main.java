package DP.백준2011_암호코드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String target;
    static long[] redDp, blueDp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();
        N = target.length();
        blueDp = new long[N];
        redDp = new long[N];
        if (isError()) {
            System.out.println(0);
            return;
        }

        redDp[0] = 1;
        for (int i = 1; i < N; i++) {
            int tmp = Integer.parseInt(target.substring(i - 1, i + 1));
            if (tmp >= 10 && tmp <= 26) {
                blueDp[i] = redDp[i - 1];
            }
//            System.out.println("blueDp[i] = " + blueDp[i]);
            if (target.charAt(i) != '0') {
                redDp[i] = blueDp[i - 1];
                redDp[i] += redDp[i - 1];
                redDp[i] %= 1000000;
            }
//            System.out.println("redDp[i] = " + redDp[i]);

        }
        System.out.println((blueDp[N - 1] + redDp[N - 1]) % 1000000);
    }

    static boolean isError() {
        if (target.startsWith("0")) {
            return true;
        }
        char prev = ' ';
        for (int i = 1; i < N; i++) {
            if (target.charAt(i) == '0' && prev == '0') {
                return true;
            }
            prev = target.charAt(i);
        }
        return false;
    }
}
