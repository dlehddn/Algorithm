package BinarySearch.백준2110번_공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list);
        int max = 0;
        int start = 1;
        int end = list.get(N - 1);

        while (start <= end) {
            int mid = (start + end) / 2;

            int cnt = 1;
            int lastIndex = 0;

            for (int i = 1; i <= N - 1; i++) {
                if (list.get(i) - list.get(lastIndex) >= mid) {
                    cnt++;
                    lastIndex = i;
                }
                if(cnt == C) break;
            }

            if (cnt >= C) {
                if (mid > max) {
                    max = mid;
                }
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(max);
    }
}
