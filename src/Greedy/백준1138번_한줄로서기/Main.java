package Greedy.백준1138번_한줄로서기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, result;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        result = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int overCnt = arr[i];
            int targetIdx = 0;
            while (overCnt > 0) {
                if (result[targetIdx] == 0) {
                    overCnt--;
                }
                targetIdx++;
            }
            while (result[targetIdx] != 0) {
                targetIdx++;
            }
            result[targetIdx] = i + 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i + " ");
        }

        System.out.println(sb);
    }
}
