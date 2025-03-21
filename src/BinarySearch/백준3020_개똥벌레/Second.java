package BinarySearch.백준3020_개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Second {
    static int N, H;
    static List<Integer> down, up;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        down = new ArrayList<>();
        up = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                up.add(Integer.parseInt(br.readLine()));
            } else {
                down.add(Integer.parseInt(br.readLine()));
            }
        }

        down.sort(Comparator.naturalOrder());
        up.sort(Comparator.naturalOrder());

        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for (int i = 0; i < H; i++) {
            int upSum = bsUp(i);
            int downSum = bsDown(i);
            int sum = upSum + downSum;
//            System.out.println("upSum = " + upSum);
//            System.out.println("downSum = " + downSum);
            if (sum <= min) {
                if (sum < min) {
                    cnt = 0;
                    min = sum;
                }
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
    }

    static int bsUp(int idx) {
        int left = 0;
        int right = up.size() - 1;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (idx < up.get(mid)) {
                right = mid - 1;
                min = Math.min(min, mid);
            } else {
                left = mid + 1;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : up.size() - min;
    }

    static int bsDown(int idx) {
        int left = 0;
        int right = down.size() - 1;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (idx >= H - down.get(mid)) {
                right = mid - 1;
                min = Math.min(min, mid);
            } else {
                left = mid + 1;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : down.size() - min;
    }
}
