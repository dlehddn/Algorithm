package Greedy.백준2217번_로프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }

        list.sort(Comparator.reverseOrder());
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < list.size(); i++) {
            max = Math.max(max, (i + 1) * list.get(i));
        }

        System.out.println(max);
    }
}
