package BinarySearch.백준2805번_나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {

    static int N, M, max;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;
        trees = new int[N];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 1000000000;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (checkAvailable(mid)) {
                left = mid + 1;
                max = Math.max(max, mid);
            } else {
                right = mid - 1;
            }
        }

        System.out.println(max);

    }

    static boolean checkAvailable(int mid) {
        long gain = 0;

        for (int height : trees) {
            if (height > mid) {
                gain += height - mid;
            }
        }

        return gain >= M ? true : false;
    }
}
