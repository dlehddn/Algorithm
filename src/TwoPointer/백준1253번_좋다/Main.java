package TwoPointer.백준1253번_좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        Arrays.sort(arr);

        if (N < 3) {
            System.out.println(0);
            return;
        }
        // -3 -1 0 1 2 4 6 10 13
        int result = 0;
        for (int i = 0; i < N; i++) {
            int target = arr[i];
            int left = i != 0 ? 0 : 1;
            int right = i != N - 1 ? N - 1 : N - 2;

            while (left < right) {
                int tmpSum = arr[left] + arr[right];
                if (tmpSum == target) {
                    result++;
                    break;
                }
                else if (tmpSum > target) {
                    if (right - 1 != i) {
                        right--;
                    } else {
                        right -= 2;
                    }
                } else {
                    if (left + 1 != i) {
                        left++;
                    } else {
                        left += 2;
                    }
                }
            }
        }
        System.out.println(result);
    }
}
