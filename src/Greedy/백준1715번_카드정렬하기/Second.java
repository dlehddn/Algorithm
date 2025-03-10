package Greedy.백준1715번_카드정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Second {
    static int N;
    static long result;
    static PriorityQueue<Long> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(br.readLine()));
        }
        if (pq.size() == 1) {
            System.out.println(0);
            return;
        }
        while (true) {
            Long poll1 = pq.poll();
            Long poll2 = pq.poll();
            result += poll1 + poll2;
            if (!pq.isEmpty()) {
                pq.add(poll1 + poll2);
            } else {
                break;
            }
        }
        System.out.println(result);
    }
}
