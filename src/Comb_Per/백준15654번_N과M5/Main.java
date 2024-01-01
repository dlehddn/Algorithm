package Comb_Per.백준15654번_N과M5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N개의 자연수 중에서 M개를 고른 수열 -> 순열
 * 출력이 오름차순이어야함 -> 입력 정렬 후 시작
 */
public class Main {

    static int N, M;
    static int[] arr, choice;
    static boolean[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        choice = new int[M];
        visited = new boolean[N];
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

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

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                choice[cnt] = arr[i];
                per(cnt + 1);
                visited[i] = false;
            }
        }
    }
}
