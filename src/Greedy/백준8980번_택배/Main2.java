package Greedy.백준8980번_택배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {

    static PriorityQueue<Post> pq = new PriorityQueue<>((p1, p2) -> {
        if (p1.to != p2.to) {
            return p1.to - p2.to;
        } else {
            return p1.from - p2.from;
        }
    });
    static PriorityQueue<Post> pq2 = new PriorityQueue<>((p1, p2) -> {
        if (p1.to != p2.to) {
            return p1.to - p2.to;
        } else {
            return p1.from - p2.from;
        }
    });
    static int N, C, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int f = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Post(f, t, c));
        }

        int curC = 0;
        int result = 0;

        while (!pq.isEmpty()) {
            Post poll = pq.poll();

            while (!pq2.isEmpty() && pq2.peek().to <= poll.from) {
                Post poll2 = pq2.poll();
                curC -= poll2.cnt;
            }

            if (C - curC >= poll.cnt) {
                pq2.add(poll);
                result += poll.cnt;
                curC += poll.cnt;
            } else if (C - curC > 0) {
                pq2.add(new Post(poll.from, poll.to, C - curC));
                result += C - curC;
                curC += C - curC;
            }
        }

        System.out.println(result);
    }

    static class Post {
        int from, to, cnt;

        public Post(int from, int to, int cnt) {
            this.from = from;
            this.to = to;
            this.cnt = cnt;
        }
    }
}
