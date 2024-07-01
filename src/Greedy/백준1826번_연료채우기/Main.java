package Greedy.백준1826번_연료채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static PriorityQueue<GasStation> pq, list;
    static int N, end, having, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new PriorityQueue<>(Comparator.comparingInt(g -> g.idx));
        pq = new PriorityQueue<>((g1, g2) -> g2.liter - g1.liter);
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.add(new GasStation(a, b));
        }
        st = new StringTokenizer(br.readLine());
        end = Integer.parseInt(st.nextToken());
        having = Integer.parseInt(st.nextToken());

        while (!list.isEmpty()) {
            if (having >= end) {
                System.out.println(result);
                return;
            }
            GasStation peek = list.peek();
            if (having >= peek.idx) {
                pq.add(peek);
                list.poll();
            } else {
                if (pq.isEmpty() && having < end) {
                    System.out.println(-1);
                    return;
                } else {
                    GasStation poll = pq.poll();
                    having += poll.liter;
                    result++;
                }
            }
        }
        while (!pq.isEmpty()) {
            GasStation poll = pq.poll();
            having += poll.liter;
            result++;
            if (having >= end) {
                System.out.println(result);
                return;
            }
        }
        System.out.println(-1);
    }

    static class GasStation {
        int idx, liter;

        public GasStation(int idx, int liter) {
            this.idx = idx;
            this.liter = liter;
        }
    }
}
