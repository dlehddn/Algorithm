package Comb_Per.백준15651번_N과M3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1부터 N까지 자연수 중에서 M개를 고른 수열, 같은 수를 여러 번 골라도 된다
 * -> 중복 순열
 */
public class Main {

    static int N, M;
    static int[] choice;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        choice = new int[M];
        sb = new StringBuilder();

        per(0);
        System.out.println(sb);

    }

    static void per(int cnt) {
        if (cnt == M) {
            for (int c : choice) {
                sb.append(c).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 1; i <= N; i++) {
            choice[cnt] = i;
            per(cnt + 1);
        }
    }
}