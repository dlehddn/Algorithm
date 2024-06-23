package Heap.백준11003번_최솟값찾기;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Number> pq;
    static int N, L;
    static int[] nums;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.value));
        sb = new StringBuilder();
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            pq.add(new Number(i, nums[i]));

            int minRange = i - L + 1;

            while (!pq.isEmpty()) {
                Number peek = pq.peek();
                int idx = peek.idx;
                if (idx < minRange) {
                    pq.poll();
                } else {
                    bw.write(peek.value + " ");
                    break;
                }
            }
        }

        bw.flush();
        bw.close();
    }

    static class Number {
        int idx, value;

        public Number(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
