package BinarySearch.백준3020_개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer> up, down;
    static int[] conflicts;
    static int N, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        up = new ArrayList<>();
        down = new ArrayList<>();
        conflicts = new int[H];

        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                up.add(Integer.parseInt(br.readLine()));
            } else {
                down.add(Integer.parseInt(br.readLine()));
            }
        }
        up.sort(Comparator.naturalOrder());
        down.sort(Comparator.naturalOrder());

        for (int i = 0; i < H; i++) {
            int i1 = bsUp(i);
            int i2 = bsDown(i);
//            System.out.println("i = " + i + ", bsUp and down = " + i1 + ", " + i2 );
            conflicts[i] = i1 + i2;
        }

//        for (int conflict : conflicts) {
//            System.out.println(conflict);
//        }
        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for (int conflict : conflicts) {
            if (min >= conflict) {
                if (min > conflict) {
                    min = conflict;
                    cnt = 0;
                }
                cnt++;
            }
        }
        System.out.println(min + " " + cnt);
    }

    static int bsUp(int h) {
        int left = 0;
        int right = up.size() - 1;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (up.get(mid) <= h) {
                left = mid + 1;
            } else {
                right = mid - 1;
                min = Math.min(min, mid);
            }
        }

        return min == Integer.MAX_VALUE ? 0 : up.size() - min;
    }

    static int bsDown(int h) {
        int left = 0;
        int right = up.size() - 1;
        int min = Integer.MAX_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (H - down.get(mid) <= h) {
                right = mid - 1;
                min = Math.min(min, mid);
            } else {
                left = mid + 1;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : down.size() - min;
    }
}
