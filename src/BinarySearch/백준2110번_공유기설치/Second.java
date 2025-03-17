package BinarySearch.백준2110번_공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Second {
    static int N, C;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 0;
        int right = 1_000_000_000;
        int result = Integer.MIN_VALUE;

        while (left <= right) {
            int targetDistance = (left + right) / 2;

            int prev = 0;
            int cur = 1;
            int cnt = 1;
            while (cur < arr.length) {
                if (arr[cur] - arr[prev] >= targetDistance) {
                    cnt++;
                    prev = cur;
                }
                cur++;
            }
            if (cnt >= C) {
                left = targetDistance + 1;
                result = Math.max(result, targetDistance);
            } else {
                right = targetDistance - 1;
            }
        }
        System.out.println(result);
    }
}
