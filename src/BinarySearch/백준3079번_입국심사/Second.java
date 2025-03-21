package BinarySearch.백준3079번_입국심사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Second {
    static int N, M;
    static int[] desks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        desks = new int[N];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            desks[i] = Integer.parseInt(br.readLine());
            max = Math.max(desks[i], max);
        }

        long left = 1;
        long right = 1_000_000_000 * M;
        long min = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            long tmp = 0;
            for (int time : desks) {
                tmp += mid/time;
                if (tmp >= M) {
                    break;
                }
            }

            if (tmp >= M) {
                right = mid - 1;
                min = Math.min(min, mid);
            } else {
                left = mid + 1;
            }
        }
        System.out.println(min);
    }
}
