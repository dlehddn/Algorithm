package Heap.백준11286번_절댓값힙;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    static PriorityQueue<Node> pq = new PriorityQueue<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                if (pq.size() != 0) {
                    sb.append(pq.poll().num + "\n");
                } else {
                    sb.append("0" + "\n");
                }
            } else {
                pq.add(new Node(command));
            }
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node>{
        int num;

        public Node(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Node o) {
            if (Math.abs(this.num) != Math.abs(o.num)) {
                return Math.abs(this.num) - Math.abs(o.num);
            } else {
                return this.num - o.num;
            }
        }
    }
}
