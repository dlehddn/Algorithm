package Recursion.백준11729번_하노이탑이동순서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        hanoi(1, 3, 2, N);
        System.out.println(cnt);
        System.out.println(sb);
    }

    static void hanoi(int start, int end, int sub, int N) {
        if (N == 0) return;

        hanoi(start, sub, end, N - 1);
        cnt++;
        sb.append(start + " " + end + "\n");

        hanoi(sub, end, start, N - 1);
    }
}
