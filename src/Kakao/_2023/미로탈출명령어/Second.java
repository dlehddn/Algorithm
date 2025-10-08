package Kakao._2023.미로탈출명령어;

// start 10:17
// end 10:40
// 23m 소요
import java.util.*;
class Second {
    // 격자 사이즈는 최대 2,500
    // k는 최대 2,500 -> 3차원 visited 배열 크기 OK
    // d, l, r, u 순 탐색
    int[] dy = {1, 0, 0, -1};
    int[] dx = {0, -1, 1, 0};
    char[] dirs = {'d', 'l', 'r', 'u'};
    String answer = "impossible";

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        dfs(0, new StringBuilder(), x-1, y-1, r-1, c-1, n, m, k, new boolean[n][m][k + 1]);
        return answer;
    }

    private void dfs(int moveCnt, StringBuilder record, int y, int x, int r, int c, int n, int m, int k, boolean[][][] visited) {
        if (!answer.equals("impossible")) {
            return;
        }
        if (moveCnt == k) {
            if (y == r && x == c) {
                answer = record.toString();
            }
            return;
        }
        visited[y][x][moveCnt] = true;

        for (int i = 0; i < 4; i++) {
            int nY = y + dy[i];
            int nX = x + dx[i];
            if (outOfRange(nY, nX, n, m) || visited[nY][nX][moveCnt + 1]) continue;
            record.append(dirs[i]);
            dfs(moveCnt + 1, record, nY, nX, r, c, n, m, k, visited);
            record.deleteCharAt(record.length() - 1);
        }
    }

    private boolean outOfRange(int y, int x, int n, int m) {
        if (y < 0 || x < 0 || y >= n || x >= m) {
            return true;
        }
        return false;
    }

    static class Coordinate {
        int y, x;
        int cnt;

        public Coordinate(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
