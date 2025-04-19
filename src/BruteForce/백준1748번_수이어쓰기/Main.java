package BruteForce.백준1748번_수이어쓰기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        long sum = 0;
        // 319535
        /**
         * 1 ~ 9
         * 10 ~ 99
         * 100 ~ 999
         * 1000 ~ 9999
         * 10000 ~ 99999
         * 100000 ~ 319535
         *
         * -> 6자리수면
         * 1의 자리수 9개
         * 10의 자리수 99 - 10 + 1 = 90개
         * 100의 자리수 999 - 100 + 1 = 900개
         * 1000의 자리수 9999 - 1000 + 1 = 9000개
         * ..
         * 100,000자리수 319535 - 100,000 + 1 = x개 다 더하기
         */
        for (int i = 1; i <= String.valueOf(N).length(); i++) {
            if (i != String.valueOf(N).length()) {
                sum += i * (Math.pow(10, i - 1) * 9);
            } else {
                sum += i * (N - Math.pow(10, i - 1) + 1);
            }
        }
        System.out.println(sum);
    }
}
