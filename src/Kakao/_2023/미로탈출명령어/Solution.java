package Kakao._2023.미로탈출명령어;

//4:00 시작
//4:36 종료
// -> 36m 소요
import java.util.*;

class Solution {
    // d, l, r, u 순
    private char[] dirs = {'d', 'l', 'r', 'u'};
    private int[] dy = {1, 0, 0, -1};
    private int[] dx = {0, -1, 1, 0};
    private boolean[][][] visited;
    private String result;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";
        visited = new boolean[k + 1][n][m];
        visited[0][x-1][y-1] = true;
        return dfs(0, k, x-1, y-1, r-1, c-1, n, m, new StringBuilder());
    }

    private String dfs(int moveCnt, int k, int curY, int curX, int r, int c, int n, int m, StringBuilder sb) {
        if (moveCnt == k) {
            if(curY == r && curX == c) {
                return sb.toString();
            }
            return "impossible";
        }

        for (int i = 0; i < 4; i++) {
            int nY = curY + dy[i];
            int nX = curX + dx[i];
            if (outOfRange(nY, nX, n, m) || visited[moveCnt+1][nY][nX]) {
                continue;
            }
            sb.append(dirs[i]);
            visited[moveCnt+1][nY][nX] = true;
            String response = dfs(moveCnt+1, k, nY, nX, r, c, n, m, sb);
            if (!response.equals("impossible")) {
                return response;
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return "impossible";
    }

    private boolean outOfRange(int y, int x, int n, int m) {
        return y < 0 || x < 0 || y >= n || x >= m;
    }
}
