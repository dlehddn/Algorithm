package Greedy.백준13904번_과제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Task> pq;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((t1, t2) -> {
            if (t1.w != t2.w) {
                return t2.w - t1.w;
            } else {
                return t1.d - t2.d;
            }
        });
        visited = new boolean[1001];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Task(d, w));
        }

        int result = 0;
        while (!pq.isEmpty()) {
            Task poll = pq.poll();

            for (int i = poll.d; i >= 1; i--) {
                if (!visited[i]) {
                    visited[i] = true;
                    result += poll.w;
                    break;
                }
            }
        }

        System.out.println(result);

    }

    static class Task {
        int d, w;

        public Task(int d, int w) {
            this.d = d;
            this.w = w;
        }
    }
}
