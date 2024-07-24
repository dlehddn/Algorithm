package Greedy.백준2110_공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, C;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken()) - 1;
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        list.sort(Comparator.naturalOrder());

        int left = 0;
        int right = 1000000000;
        int result = Integer.MIN_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;
            int cnt = 0;

            int prev = 0;
            int cur = 1;
            while (cur < list.size()) {
                if (list.get(cur) - list.get(prev) >= mid) {
                    cnt++;
                    prev = cur;
                }
                cur++;
            }
            if (cnt >= C) {
                left = mid + 1;
                result = Math.max(result, mid);
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);


    }


}
