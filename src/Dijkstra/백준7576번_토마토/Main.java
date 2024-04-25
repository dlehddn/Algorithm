package Dijkstra.백준7576번_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N, M, target, init, cur, maxDay;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        PriorityQueue<Tomato> pq = new PriorityQueue<>(Comparator.comparingInt(t -> t.days));
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != -1) {
                    target++;
                    if (map[i][j] == 1) {
                        init++;
                        pq.add(new Tomato(i, j, 0));
                    }
                }
            }
        }

        if (target == init) {
            System.out.println(0);
            return;
        }

        while (!pq.isEmpty()) {
            Tomato poll = pq.poll();

            if(visited[poll.y][poll.x]) continue;
            visited[poll.y][poll.x] = true;

            cur++;
            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if (map[nY][nX] == 0 && !visited[nY][nX]) {
                    pq.add(new Tomato(nY, nX, poll.days + 1));
                    map[nY][nX] = poll.days + 1;
                    if (maxDay < poll.days + 1) maxDay = poll.days + 1;
                }
            }

        }

        if (target != cur) {
            System.out.println(-1);
        } else {
            System.out.println(maxDay);
        }




    }

    static class Tomato {
        int y, x, days;

        public Tomato(int y, int x, int days) {
            this.y = y;
            this.x = x;
            this.days = days;
        }
    }
}
