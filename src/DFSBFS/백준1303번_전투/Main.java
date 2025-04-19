package DFSBFS.백준1303번_전투;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, sumW, sumB;
    static char[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        boolean[][] visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, visited);
                }
            }
        }
        System.out.println(sumW + " " + sumB);
    }

    static void bfs(int y, int x, boolean[][] visited) {
        char type = map[y][x];
        int cnt = 0;
        Queue<Pair> q = new ArrayDeque<>();
        visited[y][x] = true;
        q.add(new Pair(y, x));

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            cnt++;

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX) || visited[nY][nX] || map[nY][nX] != type) continue;
                q.add(new Pair(nY, nX));
                visited[nY][nX] = true;
            }
        }

        if (type == 'W') {
            sumW += Math.pow(cnt, 2);
        } else {
            sumB += Math.pow(cnt, 2);
        }
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
