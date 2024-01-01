package BackTracking.백준1987번_알파벳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static HashSet<Character> set;
    static char[][] map;
    static int R, C, max;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        set = new HashSet<>();
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        set.add(map[0][0]);
        visited[0][0] = true;
        dfs(0, 0, 0);
        System.out.println(max + 1);
    }

    static void dfs(int cnt, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if (nextX < 0 || nextY < 0 || nextY >= R || nextX >= C) continue;
            if (visited[nextY][nextX]) continue;
            if (set.contains(map[nextY][nextX])) continue;
            set.add(map[nextY][nextX]);
            visited[nextY][nextX] = true;
            dfs(cnt + 1, nextX, nextY);
            set.remove(map[nextY][nextX]);
            visited[nextY][nextX] = false;
        }
        if (cnt > max) {
            max = cnt;
        }
    }
}
