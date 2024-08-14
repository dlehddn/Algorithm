package Heap.백준1715번_카드정렬하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static int N, result;
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        if (pq.size() == 1) {
            System.out.println(pq.poll());
            return;
        }

        while (!pq.isEmpty()) {
            int n1 = pq.poll();
            int n2 = pq.poll();
            result += n1 + n2;
            if (pq.size() != 0) {
                pq.add(n1 + n2);
            }
        }

        System.out.println(result);

    }
}
