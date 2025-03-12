package TwoPointer.백준1806번_부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Second {
    static int N, S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int sum = arr[0];
        int min = Integer.MAX_VALUE;

        while (left <= right && right < N) {
            if (sum >= S) {
                min = Math.min(min, right - left + 1);
                sum -= arr[left++];
            } else {
                if (right == N - 1) break;
                sum += arr[++right];
            }
        }

        System.out.println(min != Integer.MAX_VALUE ? min : 0);
    }
}
