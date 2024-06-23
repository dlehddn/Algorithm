package Heap.백준2109번_순회강연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static PriorityQueue<Lecture> pq;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((l1, l2) -> {
            if (l1.value != l2.value) {
                return l2.value - l1.value;
            } else {
                return l1.endDate - l2.endDate;
            }
        });

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.add(new Lecture(d, p));
        }

        int result = 0;


        boolean[] visited = new boolean[10001];
        while (!pq.isEmpty()) {
            Lecture poll = pq.poll();

            for (int i = poll.endDate; i >= 1; i--) {
                if (!visited[i]) {
                    visited[i] = true;
                    result += poll.value;
                    break;
                }
            }
        }

        System.out.println(result);
    }

    static class Lecture {
        int endDate, value;

        public Lecture(int endDate, int value) {
            this.endDate = endDate;
            this.value = value;
        }
    }
}
