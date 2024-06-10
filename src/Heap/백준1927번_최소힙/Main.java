package Heap.백준1927번_최소힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                if (pq.size() != 0) {
                    sb.append(pq.poll());
                } else {
                    sb.append(0);
                }
                sb.append("\n");
            } else {
                pq.add(command);
            }
        }
        System.out.println(sb);
    }
}
