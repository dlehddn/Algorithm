package BinarySearch.백준3079번_입국심사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] desks;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        desks = new int[N];
        for (int i = 0; i < N; i++) {
            desks[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, desks[i]);
        }

        long left = 1;
        long right = (long) max * M;
        long result = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;
            long acceptableSize = 0;

            for (int proceedingTime : desks) {
                acceptableSize += mid / proceedingTime;
                if (acceptableSize >= M) {
                    break;
                }
            }

            if (acceptableSize >= M) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(result);
    }
}
