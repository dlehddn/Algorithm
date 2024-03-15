package BruteForce.백준2512번_예산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, total;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            arr[i] = cur;
        }
        Arrays.sort(arr);
        total = Integer.parseInt(br.readLine());

        dp[0] = arr[0];
        for (int i = 1; i < N; i++) {
            dp[i] = dp[i - 1] + arr[i];
        }

        int edge = -1;
        for (int i = 0; i < N; i++) {
            int compare = dp[i] + arr[i] * (N - 1 - i);
            if (compare > total) {
                edge = i;
                break;
            }
        }

        if (edge != -1) {
            int lastEdge = edge;
            while (true) {
                try {
                    if (arr[lastEdge] != arr[lastEdge + 1]) break;
                    lastEdge++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }

            int target = arr[edge] - 1;
            int sum = edge == 0 ? 0 : dp[edge - 1];
            while (true) {
                if (sum + target * (N - edge) <= total) break;
                target--;
            }
            System.out.println(target);
        } else {
            System.out.println(arr[N - 1]);
        }

    }

}
