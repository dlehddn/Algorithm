package Greedy.백준1026번_보물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Integer> a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new ArrayList<>();
        b = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            a.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            b.add(Integer.parseInt(st.nextToken()));
        }
        a.sort(Comparator.naturalOrder());
        b.sort(Comparator.reverseOrder());

        int result = 0;
        for (int i = 0; i < a.size(); i++) {
            result += a.get(i) * b.get(i);
        }

        System.out.println(result);
    }
}
