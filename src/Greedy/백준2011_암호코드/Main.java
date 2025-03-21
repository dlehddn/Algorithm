package Greedy.백준2011_암호코드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String target;
    static long linear;
    static long combine;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = br.readLine();

        linear = 1;
        combine = 0;
        if (target.startsWith("0")) {
            System.out.println(0);
            return;
        }
        for (int i = 1; i < target.length(); i++) {
            int prev = target.charAt(i - 1) - '0';
            int cur = target.charAt(i) - '0';
            int tmp = prev * 10 + cur;
            if (tmp == 0 || tmp > 26 && tmp % 10 == 0) {
                System.out.println(0);
                return;
            }
            if (tmp % 10 == 0) {
                combine = linear;
                linear = 0;
                continue;
            }
            if (tmp > 26) {
                linear = (linear + combine) % 1000000;
                combine = 0;
            } else {
                long tmp2 = linear;
                linear = (linear + combine) % 1000000;
                combine = tmp2;
            }
        }
        System.out.print((linear + combine) % 1000000);
    }
}
