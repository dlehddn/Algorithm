package Greedy.백준1781번_컵라면;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static PriorityQueue<Ramen> pq;
    static int N;
    static List<Ramen>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((r1, r2) -> {
            if (r1.value != r2.value) {
                return r2.value - r1.value;
            } else {
                return r1.deadLine - r2.deadLine;
            }
        });
        list = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            list[d].add(new Ramen(d, v));
        }

        int max = 0;

        for (int i = N; i >= 1; i--) {
            for (Ramen ramen : list[i]) {
                pq.add(ramen);
            }
            if (pq.isEmpty()) continue;

            Ramen poll = pq.poll();
            max += poll.value;
        }


        System.out.println(max);
    }

    static class Ramen {
        int deadLine, value;

        public Ramen(int deadLine, int value) {
            this.deadLine = deadLine;
            this.value = value;
        }
    }
}
