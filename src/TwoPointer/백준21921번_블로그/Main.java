package TwoPointer.백준21921번_블로그;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = K;
        long sum = 0;

        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }

        long max = sum;
        int concurrent = 1;

        while (right < N) {
            sum += arr[right++];
            sum -= arr[left++];

            if (sum > max) {
                max = sum;
                concurrent = 1;
            } else if (sum == max) {
                concurrent++;
            }
        }

        System.out.println(max != 0 ? max : "SAD");
        if (max != 0) {
            System.out.println(concurrent);
        }


    }
}
