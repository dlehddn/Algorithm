package Comb_Per.백준15650번_N과M2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.SimpleTimeZone;
import java.util.StringTokenizer;

/**
 * 1부터 N까지 자연수 중에서 중복없이 M개를 고른 수열
 * + 고른 수열이 오름차순이어야 한다. -> 조합
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
        comb(0, 1);
        System.out.println(sb);
    }

    static void comb(int cnt, int start) {

        if (cnt == M) {
            for (int c : choice) {
                sb.append(c).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            choice[cnt] = i;
            comb(cnt + 1, i + 1);
        }
    }
}
