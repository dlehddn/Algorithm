package Heap.백준1781번_컵라면;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static PriorityQueue<Problem> pq = new PriorityQueue<>((p1, p2) -> {
        if (p1.award != p2.award) {
            return p2.award - p1.award;
        } else {
            return p1.deadLine - p2.deadLine;
        }
    });
    static List<Problem>[] list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        list = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            list[d].add(new Problem(d, a));
        }
        int result = 0;

        for (int i = N; i >= 1; i--) {
            for (Problem p : list[i]) {
                pq.add(p);
            }
            if (pq.isEmpty()) continue;
            Problem poll = pq.poll();
            result += poll.award;
        }


        System.out.println(result);
    }

    static class Problem {
        int deadLine, award;

        public Problem(int deadLine, int award) {
            this.deadLine = deadLine;
            this.award = award;
        }
    }
}
