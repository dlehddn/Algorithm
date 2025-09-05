package TwoPointer.백준1806번_부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fourth {
    static int[] arr;
    static int N, S;
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

        int sum = arr[0];
        int min = Integer.MAX_VALUE;
        int left = 0, right = 0;

        while (left <= right && right < N) {
            if (sum < S) {
                if (right + 1 == N) {
                    break;
                }
                right++;
                sum += arr[right];
            } else {
                min = Math.min(min, right - left + 1);
                sum -= arr[left];
                left++;
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? 0 : min);
    }
}
