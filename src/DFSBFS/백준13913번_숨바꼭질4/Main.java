package DFSBFS.백준13913번_숨바꼭질4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Queue<Position> q = new LinkedList<>();
    static boolean[] visited = new boolean[100001];
    static int[] moveLog = new int[100001];
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        q.add(new Position(N, 0));
        visited[N] = true;

        while (!q.isEmpty()) {
            Position poll = q.poll();

            if (poll.x == K) {
                System.out.println(poll.time);
                System.out.println(traceLog());
                return;
            }

            if (poll.x - 1 >= 0 && !visited[poll.x - 1]) {
                q.add(new Position(poll.x - 1, poll.time + 1));
                moveLog[poll.x - 1] = poll.x;
                visited[poll.x - 1] = true;
            }

            if (poll.x + 1 <= 100000 && !visited[poll.x + 1]) {
                q.add(new Position(poll.x + 1, poll.time + 1));
                moveLog[poll.x + 1] = poll.x;
                visited[poll.x + 1] = true;
            }

            if (poll.x * 2 <= 100000 && !visited[poll.x * 2]) {
                q.add(new Position(poll.x * 2, poll.time + 1));
                moveLog[poll.x * 2] = poll.x;
                visited[poll.x * 2] = true;
            }

        }

    }

    static StringBuilder traceLog() {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.push(K);

        while (K != N) {
            stack.push(moveLog[K]);
            K = moveLog[K];
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        return sb;
    }

    static class Position {
        int x, time;

        public Position(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }
}
