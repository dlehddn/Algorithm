package BinarySearch.백준1654번_랜선자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int K, N;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);

        long start = 1;
        long end = list.get(K - 1);
        long max = 0;

        while (start <= end) {
            long mid = (start + end) / 2;

            long cnt = 0;
            for (int i = 0; i < K; i++) {
                cnt += list.get(i) / mid;
            }

            if (cnt >= N) {
                start = mid + 1;
                if (mid > max) {
                    max = mid;
                }
            } else {
                end = mid - 1;
            }
        }
        System.out.println(max);
    }
}
