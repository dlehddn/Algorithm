package DFSBFS.백준1012번_유기농배추;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int T, M, N, K;
    static StringBuilder sb = new StringBuilder();
    static int[][] map;
    static boolean[][] visited;
    static int result;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visited = new boolean[N][M];
            result = 0;
            for (int j = 0; j < K; j++) {
                String[] split = br.readLine().split(" ");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                map[y][x] = 1;
            }
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    bfs(j, k);
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void bfs(int y, int x) {
        if (visited[y][x] || map[y][x] == 0) return;
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(y, x));
        visited[y][x] = true;
        result++;

        while (!q.isEmpty()) {
            Pair poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX)) continue;
                if (!visited[nY][nX] && map[nY][nX] == 1) {
                    visited[nY][nX] = true;
                    q.add(new Pair(nY, nX));
                }
            }
        }
    }

    private static boolean outOfRange(int y, int x) {
        return y >= N || x >= M || y < 0 || x < 0;
    }

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
