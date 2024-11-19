package DFSBFS.백준7576번_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, goal, cur;
    static int[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static Queue<Pair> q = new LinkedList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int state = Integer.parseInt(st.nextToken());
                if (state == 0 || state == 1) {
                    goal++;
                    if (state == 1) {
                        q.add(new Pair(i, j));
                        cur++;
                    }
                }
                map[i][j] = state;
            }
        }
        if (goal == cur) {
            System.out.println(0);
            return;
        }

        int day = 0;

        while (true) {
            day++;
            int before = cur;
            bfs(q, visited);
            if (goal == cur) {
                System.out.println(day);
                return;
            }
            if (cur == before) {
                System.out.println(-1);
                return;
            }
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static void bfs(Queue<Pair> q, boolean[][] visited) {
        int cnt = q.size();

        while (!q.isEmpty() && cnt > 0) {
            Pair poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX)) {
                    continue;
                }
                if (!visited[nY][nX] && map[nY][nX] == 0) {
                    visited[nY][nX] = true;
                    q.add(new Pair(nY, nX));
                    cur++;
                }
            }
            cnt--;
        }
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }
}
