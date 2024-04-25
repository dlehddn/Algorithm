package Heap.백준1655번_가운데를말해요;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static int N, center;
    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Integer> minHeap, maxHeap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        center = Integer.parseInt(br.readLine());
        sb.append(center + "\n");

        for (int i = 1; i < N; i++) {
            int newNum = Integer.parseInt(br.readLine());
            if (newNum >= center) {
                minHeap.add(newNum);
            } else {
                maxHeap.add(newNum);
            }

            if (minHeap.size() - maxHeap.size() >= 2) {
                maxHeap.add(center);
                center = minHeap.poll();
            } else if (maxHeap.size() - minHeap.size() >= 1) {
                minHeap.add(center);
                center = maxHeap.poll();
            }

            sb.append(center + "\n");
        }

        System.out.println(sb);


    }
}
