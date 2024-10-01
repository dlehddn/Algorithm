package Heap.백준13913번_숨바꼭질4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;
    static boolean[] visited;
    static int[] records;
    static PriorityQueue<Position> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.time));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        records = new int[100001];

        pq.add(new Position(0, N));
        int result = 0;

        while (!pq.isEmpty()) {
            Position poll = pq.poll();

            if (poll.d == K) {
                result = poll.time;
                break;
            }

            if (poll.d - 1 >= 0 && !visited[poll.d - 1]) {
                pq.add(new Position(poll.time + 1, poll.d - 1));
                records[poll.d - 1] = poll.d;
                visited[poll.d - 1] = true;
            }
            if (poll.d + 1 <= 100000 && !visited[poll.d + 1]) {
                pq.add(new Position(poll.time + 1, poll.d + 1));
                records[poll.d + 1] = poll.d;
                visited[poll.d + 1] = true;
            }
            if (poll.d * 2 <= 100000 && !visited[poll.d * 2]) {
                pq.add(new Position(poll.time + 1, poll.d * 2));
                records[poll.d * 2] = poll.d;
                visited[poll.d * 2] = true;
            }
        }

        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();


        while (true) {
            list.add(K);
            if (K == N) break;
            K = records[K];
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i) + " ");
        }

        System.out.println(result);
        System.out.println(sb);
    }

    static class Position {
        int time, d;

        public Position(int time, int d) {
            this.time = time;
            this.d = d;
        }
    }
}
