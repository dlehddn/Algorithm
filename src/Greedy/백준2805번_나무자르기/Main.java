package Greedy.백준2805번_나무자르기;

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

        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            int cur = list.get(i);
            int next = i != list.size() - 1 ? list.get(i + 1) : 0;

            long max = sum + (long) (cur - next) * (i + 1);
            if (max >= M) {
                long value = sum;
                for (int j = cur; j >= next; j--) {
                    if (value >= M) {
                        System.out.println(j);
                        return;
                    }
                    value += i + 1;
                }
            } else {
                sum = max;
            }
        }
    }
}
