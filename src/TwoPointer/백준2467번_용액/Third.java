package TwoPointer.백준2467번_용액;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1, 2, 4, 6, 8
 * -7, -4, -3, -2, -1
 */
public class Third {
    static int N;
    static int[] arr;
    static int[] result = new int[2];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // first step
        int left = 0, right = N - 1;
        while (left < right) {
            int tmpSum = arr[left] + arr[right];
            if (Math.abs(tmpSum) < min) {
                min = Math.abs(tmpSum);
                result[0] = arr[left];
                result[1] = arr[right];
            }
            if (tmpSum < 0) {
                left++;
            } else if (tmpSum > 0) {
                right--;
            } else {
                break;
            }
        }
        System.out.println(result[0] + " " + result[1]);
    }
}
