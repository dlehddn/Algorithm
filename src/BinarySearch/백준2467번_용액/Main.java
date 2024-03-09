package BinarySearch.백준2467번_용액;

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


        int sum = Integer.MAX_VALUE;
        int leftValue = 0;
        int rightValue = 0;

        loop:
        for (int i = 0; i < N - 1; i++) {
            int left = i + 1;
            int right = N - 1;
            int target = arr[i] * -1;
            while (left <= right) {
                int mid = (left + right) / 2;
                int tmp = Math.abs(arr[i] + arr[mid]);
                if (tmp < sum) {
                    sum = tmp;
                    leftValue = arr[i];
                    rightValue = arr[mid];
                }
                if (arr[mid] < target) {
                    left = mid + 1;
                } else if (arr[mid] > target) {
                    right = mid - 1;
                } else {
                    break loop;
                }
            }
        }
        System.out.println(leftValue + " " + rightValue);

        // -99 -98 -97 -96 -95 1 2 3 4 10 19
    }
}
