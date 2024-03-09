package TwoPointer.백준2467번_용액;

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


        int left = 0;
        int right = N - 1;
        int sum = Integer.MAX_VALUE;
        int leftValue = 0;
        int rightValue = 0;
        // -99 -98 -97 -96 -95 1 2 3 4 10 19

        while (left < right) {
            if (Math.abs(arr[left] + arr[right]) < sum) {
                sum = Math.abs(arr[left] + arr[right]);
                leftValue = arr[left];
                rightValue = arr[right];
            }
            if (arr[left] + arr[right] > 0) {
                right--;
            } else if (arr[left] + arr[right] < 0) {
                left++;
            } else break;
        }
        System.out.println(leftValue + " " + rightValue);
    }
}
