package Heap.백준2109번_순회강연;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {

    static int N, result;
    static PriorityQueue<Lecture> pq;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>((l1, l2) -> {
            if (l1.pay != l2.pay) {
                return l2.pay - l1.pay;
            } else {
                return l2.day - l1.day;
            }
        });
        visited = new boolean[10001];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            pq.add(new Lecture(p, d));
        }

        while (!pq.isEmpty()) {
            Lecture poll = pq.poll();
            for (int i = poll.day; i >= 1; i--) {
                if (!visited[i]) {
                    result += poll.pay;
                    visited[i] = true;
                    break;
                }
            }
        }

        System.out.println(result);


    }

    static class Lecture {
        int pay, day;

        public Lecture(int pay, int day) {
            this.pay = pay;
            this.day = day;
        }
    }
}
