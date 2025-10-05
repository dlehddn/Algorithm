package Greedy.백준15903번_카드합체놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static PriorityQueue<Long> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split(" ");
        N = Integer.parseInt(split[0]);
        M = Integer.parseInt(split[1]);
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        while (M > 0) {
            Long card1 = pq.poll();
            Long card2 = pq.poll();
            pq.add(card1 + card2);
            pq.add(card1 + card2);
            M--;
        }

        long result = 0;
        while (!pq.isEmpty()) {
            result += pq.poll();
        }

        System.out.println(result);
    }
}
