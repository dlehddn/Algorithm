package Greedy.백준2212번_센서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static List<Integer> distances;
    static PriorityQueue<Integer> diffs;
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        distances = new ArrayList<>();
        diffs = new PriorityQueue<>(Comparator.reverseOrder());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int d = Integer.parseInt(st.nextToken());
            distances.add(d);
        }

        distances.sort(Comparator.naturalOrder());

        for (int i = 1; i < N; i++) {
            diffs.add(distances.get(i) - distances.get(i - 1));
        }


        while (!diffs.isEmpty() && K > 1) {
            diffs.poll();
            K--;
        }

        int result = 0;
        while (!diffs.isEmpty()) {
            result += diffs.poll();
        }

        System.out.println(result);

    }
}
