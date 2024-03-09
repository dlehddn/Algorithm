package TwoPointer.백준1806번_부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

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

        /**
         *
         */
        int left = 0;
        int right = 0;
        int sum = arr[left];
        int min = Integer.MAX_VALUE;

        while (left < N && right < N) {
            if (sum >= S) {
                int dist = right - left + 1;
                if (dist < min) {
                    min = dist;
                }
                sum -= arr[left];
                left++;
            } else {
                if(right == N - 1) break;
                right++;
                sum += arr[right];
            }
        }


        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }


    }
}
