package BackTracking.백준1987번_알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main2 {

    static int[][] map;
    static int R, C, max;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[26];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j) - 'A';
            }
        }

        dfs(0, 0, 0);
        if (max == 0) {
            System.out.println(1);
        } else System.out.println(max);
    }

    static void dfs(int cnt, int x, int y) {
        if (visited[map[y][x]]) {
            if (cnt > max) {
                max = cnt;
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if(nextY < 0 || nextX < 0 || nextY >= R || nextX >= C) continue;
            visited[map[y][x]] = true;
            dfs(cnt + 1, nextX, nextY);
            visited[map[y][x]] = false;
        }
    }
}
