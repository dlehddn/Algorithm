package Heap.백준2110번_공유기설치;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, C, left, right, result;
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        list.sort(Comparator.naturalOrder());
        left = 1;
        right = list.get(list.size() - 1) - list.get(0);

        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid)) {
//                System.out.println("가능" + mid);
                left = mid + 1;
                result = Math.max(result, mid);
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }

    static boolean check(int mid) {
        int idx = 0;
        int cnt = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) - list.get(idx) >= mid) {
                cnt++;
                idx = i;
            }
        }
//        System.out.println(mid + ": " + cnt);
        if (cnt >= C) return true;
        else return false;
    }

}
