package DFSBFS.백준3109번_빵집;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int R, C, result;
    static char[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 0, 1};
    static int[] dx = {1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            dfs(i, 0);
        }

        System.out.println(result);

    }

    static boolean dfs(int y, int x) {
        visited[y][x] = true;

        if (x == C - 1) {
            result++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            /**
             * 우상, 우, 우하 방향으로 체크해야함
             * 벽 or 방문했으면 진입 못하도록
             */
            int nextY = y + dy[i];
            int nextX = x + dx[i];
            if(nextY < 0 || nextX < 0 || nextY >= R || nextX >= C) continue;
            if(visited[nextY][nextX] || map[nextY][nextX] == 'x') continue;
            /**
            * 다음 자리의 도달 여부에 따라 true를 반환할지, 다음 루프로 넘어갈지 결정
            */
            boolean arrive = dfs(nextY, nextX);
            if (arrive) {
                return true;
            }
        }
        /**
        * 끝내 true를 반환하지 못했다면 3가지 방향 어디로 가도 목적지에 도달할 수 없는 것!
        * -> false 반환
        */
        return false;
    }
}
