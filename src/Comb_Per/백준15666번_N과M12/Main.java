package Comb_Per.백준15666번_N과M12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;
import java.util.StringTokenizer;

/**
 * N개의 자연수 중에서 M개를 고른 수열 + 비내림차순이어야 한다 -> 조합
 * 같은 수를 여러 번 골라도 된다 -> 중복 조합
 * 입력에 중복된 값 있을 수 있음 + 정렬 안되어있음 -> 정렬 하고 시작, 같은 값 나오면 패스
 */
public class Main {

    static int[] arr, choice;
    static int N, M, prev;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        choice = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        comb(0, 0);
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

        for (int i = start; i < N; i++) {
            if(prev == arr[i]) continue;
            choice[cnt] = arr[i];
            comb(cnt + 1, i);
            prev = arr[i];
        }
    }
}
