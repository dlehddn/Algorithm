package BinarySearch.백준2805번_나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        list.sort(Comparator.reverseOrder());

        int max = Integer.MIN_VALUE;
        int left = 0;
        int right = list.get(0);

        while (left <= right) {
            int mid = (left + right) / 2;
            long sum = calculate(mid);
            if (sum >= M) {
                if (mid > max) max = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(max);
    }

    static long calculate (int cut) {
        long sum = 0;

        for (Integer n : list) {
            if (cut >= n) continue;

            sum += n - cut;
        }
        return sum;
    }
}
