package Greedy.백준2212번_센서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Retry {
    static int N, K;
    static List<Integer> sensors;
    static PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        sensors = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            sensors.add(Integer.parseInt(st.nextToken()));
        }
        sensors = sensors.stream()
                .distinct()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
        for (int i = 0; i < sensors.size() - 1; i++) {
            pq.add(sensors.get(i + 1) - sensors.get(i));
        }
        int sum = 0;
        int tryCnt = sensors.size() - K;

        while (tryCnt > 0) {
            sum += pq.poll();
            tryCnt--;
        }

        System.out.println(sum);
    }
}
