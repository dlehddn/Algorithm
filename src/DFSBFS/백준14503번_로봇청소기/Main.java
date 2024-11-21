package DFSBFS.백준14503번_로봇청소기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] cleaned;
    static int[] dy = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cleaned = new boolean[N][M];
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        Robot robot = new Robot(r, c, d, 0);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(robot);
        System.out.println(robot.cnt);
    }

    static void dfs(Robot robot) {
        if (!cleaned[robot.y][robot.x]) {
            cleaned[robot.y][robot.x] = true;
            robot.cnt++;
        }
        if (allCleaned(robot)) {
            if (canGoBack(robot)) {
                goBack(robot);
                dfs(robot);
            } else {
            }
        } else {
            rotate(robot);
            tryGoStraight(robot);
            dfs(robot);
        }
    }

    static void tryGoStraight(Robot robot) {
        int nY = robot.y + dy[robot.d];
        int nX = robot.x + dx[robot.d];
        if (!outOfRange(nY, nX) && map[nY][nX] == 0 && !cleaned[nY][nX]) {
            robot.y = nY;
            robot.x = nX;
        }
    }

    static void rotate(Robot robot) {
        robot.d = (robot.d + 3) % 4;
    }

    static void goBack(Robot robot) {
        int nY = robot.y + dy[(robot.d + 2) % 4];
        int nX = robot.x + dx[(robot.d + 2) % 4];
        robot.y = nY;
        robot.x = nX;
    }

    static boolean canGoBack(Robot robot) {
        int nY = robot.y + dy[(robot.d + 2) % 4];
        int nX = robot.x + dx[(robot.d + 2) % 4];
        if (outOfRange(nY, nX) || map[nY][nX] == 1) {
            return false;
        }
        return true;
    }

    static boolean allCleaned(Robot robot) {
        for (int i = 0; i < 4; i++) {
            int nY = robot.y + dy[i];
            int nX = robot.x + dx[i];
            if (outOfRange(nY, nX)) {
                continue;
            }
            if (map[nY][nX] == 0 && !cleaned[nY][nX]) {
                return false;
            }
        }
        return true;
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }


    static class Robot {
        int y, x, d, cnt;

        public Robot(int y, int x, int d, int cnt) {
            this.y = y;
            this.x = x;
            this.d = d;
            this.cnt = cnt;
        }
    }
}
