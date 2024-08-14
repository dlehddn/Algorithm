package Simulation.백준2573번_빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int N, M;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            year++;
            int remain = melt();
            if (remain == 0) {
                System.out.println(0);
                return;
            }
            if (isFinished(remain)) {
                System.out.println(year);
                return;
            }
        }
    }

    static boolean isFinished(int remain) {
        Pair start = findStart();
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(start);
        visited[start.y][start.x] = true;

        int cnt = 0;

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (!outOfRange(nY, nX) && !visited[nY][nX] && map[nY][nX] > 0) {
                    q.add(new Pair(nY, nX));
                    visited[nY][nX] = true;
                }
            }
        }

        return cnt == remain ? false : true;
    }

    static Pair findStart() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    return new Pair(i, j);
                }
            }
        }
        return new Pair(0, 0);
    }

    static int melt() {
        int[][] copied = deepCopy(map);
        int remain = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    for (int k = 0; k < 4; k++) {
                        int nY = i + dy[k];
                        int nX = j + dx[k];
                        if (!outOfRange(nY, nX) && map[nY][nX] == 0) {
                            copied[i][j] = Math.max(0, copied[i][j] - 1);
                        }
                    }
                    if (copied[i][j] > 0) remain++;
                }
            }
        }
        map = copied;
        return remain;
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static int[][] deepCopy(int[][] origin) {
        int[][] copied = new int[origin.length][origin[0].length];
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < origin[0].length; j++) {
                copied[i][j] = origin[i][j];
            }
        }
        return copied;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
