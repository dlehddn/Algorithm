package Greedy.백준13305_주유소;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static List<Integer> prices, meters;
    static long result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prices = new ArrayList<>();
        meters = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            meters.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prices.add(Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < N - 1; i++) {
            pq.add(prices.get(i));
            result += (long) meters.get(i) * pq.peek();
        }

        System.out.println(result);
    }
}
