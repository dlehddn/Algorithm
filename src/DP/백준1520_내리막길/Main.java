package DP.백준1520_내리막길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map, dp;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];
        Arrays.stream(dp)
                .forEach(arr -> Arrays.fill(arr, -1));
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dp_dfs(0, 0));

    }

    static int dp_dfs(int y, int x) {
        if (y == N - 1 && x == M - 1) {
            return 1;
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;
        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if(nextY < 0 || nextX < 0 || nextX >= M || nextY >= N) continue;
            if (map[nextY][nextX] < map[y][x]) {
                dp[y][x] += dp_dfs(nextY, nextX);
            }
        }
        return dp[y][x];
    }
}
