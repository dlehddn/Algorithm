package DFSBFS.백준5014_스타트링크;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int F, S, G, U, D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[F + 1];
        Queue<Floor> q = new ArrayDeque<>();
        q.add(new Floor(S, 0));
        visited[S] = true;

        while (!q.isEmpty()) {
            Floor poll = q.poll();

            if (G == poll.num) {
                System.out.println(poll.cnt);
                return;
            }

            if (poll.num + U <= F && !visited[poll.num + U]) {
                q.add(new Floor(poll.num + U, poll.cnt + 1));
                visited[poll.num + U] = true;
            }

            if (poll.num - D >= 1 && !visited[poll.num - D]) {
                q.add(new Floor(poll.num - D, poll.cnt + 1));
                visited[poll.num - D] = true;
            }
        }
        System.out.println("use the stairs");
    }

    static class Floor {
        int num, cnt;

        public Floor(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
