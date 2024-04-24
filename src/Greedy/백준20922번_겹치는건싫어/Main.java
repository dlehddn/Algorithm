package Greedy.백준20922번_겹치는건싫어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr, store;
    static int N, K, max, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        store = new int[100001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        cnt++;
        store[arr[0]]++;

        while (true) {
            if (store[arr[right + 1]] < K) {
                right++;
                cnt++;
                store[arr[right]]++;
            } else {
                if (max < cnt) max = cnt;
                cnt--;
                store[arr[left]]--;
                left++;
            }

            if (right == N - 1) {
                if (max < cnt) max = cnt;
                break;
            }
        }

        System.out.println(max);
    }
}
