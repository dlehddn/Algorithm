package Greedy.백준8980번_택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, C, M;
    static PriorityQueue<Post> pq;
    static int[] caps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.end));
        caps = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            pq.add(new Post(start, end, value));
        }

        int result = 0;
        while (!pq.isEmpty()) {
            Post poll = pq.poll();
            int max = 0;
            for (int i = poll.start; i < poll.end; i++) {
                if (max < caps[i]) {
                    max = caps[i];
                }
            }
            int remain = C - max > 0 ? C - max : 0;
            int add = remain > poll.boxes ? poll.boxes : remain;

            if (add > 0) {
                result += add;
                for (int i = poll.start; i < poll.end; i++) {
                    caps[i] += add;
                }
            }
        }

        System.out.println(result);
    }

    static class Post {
        int start, end, boxes;

        public Post(int start, int end, int boxes) {
            this.start = start;
            this.end = end;
            this.boxes = boxes;
        }
    }
}
