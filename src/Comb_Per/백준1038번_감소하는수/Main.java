package Comb_Per.백준1038번_감소하는수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    static int N;
    static List<Long> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            comb(new int[i], 0, i, 0);
        }

        result.sort(Comparator.naturalOrder());
        if (N >= result.size()) {
            System.out.println(-1);
        } else {
            System.out.println(result.get(N));
        }

    }

    static void comb(int[] selected, int cnt, int goal, int start) {
        if (cnt == goal) {
            String reduce = Arrays.stream(selected)
                    .boxed()
                    .sorted(Comparator.reverseOrder())
                    .map(i -> Integer.toString(i))
                    .reduce("", (s1, s2) -> s1 + s2);
            result.add(Long.parseLong(reduce));
            return;
        }

        for (int i = start; i <= 9; i++) {
            selected[cnt] = i;
            comb(selected, cnt + 1, goal, i + 1);
        }
    }


}
