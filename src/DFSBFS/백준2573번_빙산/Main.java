package DFSBFS.백준2573번_빙산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
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
            melt();
            int amount = findAmount();
            if (amount == 0) {
                System.out.println(0);
                return;
            } else if (amount >= 2) {
                System.out.println(year);
                return;
            }
        }
    }

    static int findAmount() {
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j] && map[i][j] > 0) {
                    cnt++;
                    bfs(i, j, visited);
                }
            }
        }
        return cnt;
    }

    static void bfs(int y, int x, boolean[][] visited) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Pair poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX) || map[nY][nX] == 0 || visited[nY][nX]) {
                    continue;
                }
                q.add(new Pair(nY, nX));
                visited[nY][nX] = true;
            }
        }
    }

    static void melt() {
        int[][] copiedMap = deepCopy();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    int meltCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nY = i + dy[k];
                        int nX = j + dx[k];
                        if (outOfRange(nY, nX)) {
                            continue;
                        }
                        if (map[nY][nX] == 0) {
                            meltCnt++;
                        }
                    }
                    copiedMap[i][j] = Math.max(0, map[i][j] - meltCnt);
                }
            }
        }
        map = copiedMap;
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static int[][] deepCopy() {
        int[][] copied = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copied[i][j] = map[i][j];
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
