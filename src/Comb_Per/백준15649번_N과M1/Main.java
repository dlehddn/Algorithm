package Comb_Per.백준15649번_N과M1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열 -> 순열
 */
public class Main {

    static int N, M;
    static int[] choice;
    static boolean[] visited;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        choice = new int[M];
        visited = new boolean[N + 1];
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
            if (!visited[i]) {
                visited[i] = true;
                choice[cnt] = i;
                per(cnt + 1);
                visited[i] = false;
            }
        }
    }
}
