package TwoPointer.백준22945_팀빌딩;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        long max = 0;

        while (left < right) {
            max = Math.max(max, (long) Math.min(arr[left], arr[right]) * (right - left - 1));
            if (left + 1 == right) {
                break;
            }

            // 왜 안되냐?
            if (Math.min(arr[left + 1], arr[right]) > Math.min(arr[left], arr[right - 1])) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(max);
    }
}
