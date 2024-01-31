package DFSBFS.백준2169번_로봇조종하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][][] dp;
    static int[] dy = {0, 1, 0};
    static int[] dx = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], Integer.MIN_VALUE / 2);
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
            }
        }

        System.out.println(dp_dfs(0, 0, 0, new boolean[N][M]));

    }

    static int dp_dfs(int y, int x, int dir, boolean[][] visited) {
        if (y == N - 1 && x == M - 1) {
            return map[y][x];
        }
        if (dp[y][x][dir] != Integer.MIN_VALUE / 2) return dp[y][x][dir];

        visited[y][x] = true;
        for (int i = 0; i < 3; i++) {
            int nY = y + dy[i];
            int nX = x + dx[i];
            if (outOfRange(nY, nX)) continue;
            if (!visited[nY][nX]) {
                dp[y][x][dir] = Math.max(dp[y][x][dir], dp_dfs(nY, nX, i, visited) + map[y][x]);
            }
        }
        visited[y][x] = false;
        return dp[y][x][dir];
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }
}
