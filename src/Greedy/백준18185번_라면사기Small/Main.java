package Greedy.백준18185번_라면사기Small;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        // 3개 구매 탐색
        for (int i = 0; i < N - 2; i++) {
            if (arr[i + 1] > arr[i + 2]) {
                int min = Math.min(arr[i], arr[i + 1] - arr[i + 2]);
                result += 5 * min;
                arr[i] -= min;
                arr[i + 1] -= min;
            }
            int min = Math.min(Math.min(arr[i], arr[i + 1]), arr[i + 2]);
            result += 7 * min;
            arr[i] -= min;
            arr[i + 1] -= min;
            arr[i + 2] -= min;
        }

        for (int i = 0; i < N - 1; i++) {
            if (arr[i] != 0 && arr[i + 1] != 0) {
                int min = Math.min(arr[i], arr[i + 1]);
                result += 5 * min;
                arr[i] -= min;
                arr[i + 1] -= min;
            }
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] != 0) {
                result += 3 * arr[i];
            }
        }

        System.out.println(result);

    }
}
