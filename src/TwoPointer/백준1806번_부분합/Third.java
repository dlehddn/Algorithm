package TwoPointer.백준1806번_부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Third {
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

        long min = Long.MAX_VALUE;
        long sum = arr[0];
        int left = 0;
        int right = 0;
        if (sum >= S) {
            min = 1;
        }
        while (left <= right && right < N) {
            if (sum < S) {
                if (right + 1 == N) {
                    break;
                }
                sum += arr[++right];
            } else  {
                min = Math.min(min, right - left + 1);
                sum -= arr[left++];
            }
        }

        System.out.println(min == Long.MAX_VALUE ? 0 : min);
    }
}
