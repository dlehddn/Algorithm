package TwoPointer.백준2467번_용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Second {
    // 1 5 7 10 23
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        int[] result = new int[2];
        int min = Integer.MAX_VALUE;

        while (left < right) {
            int tmpSum = arr[left] + arr[right];
            if (min > Math.abs(tmpSum)) {
                min = Math.abs(tmpSum);
                result[0] = arr[left];
                result[1] = arr[right];
            }
            if (tmpSum > 0) {
                right--;
            } else if (tmpSum < 0) {
                left++;
            } else {
                break;
            }
        }

        System.out.println(result[0] + " " + result[1]);
    }
}