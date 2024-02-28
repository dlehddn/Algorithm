package Kakao._2023.q6;

import java.util.Arrays;

class Solution {
    /**
     * 단순히 완전탐색을 하면 최대 4^2500의 시간 복잡도가 발생, 말도 안되는거다.
     * 같은 격자를 두 번 이상 방문할 수 있다?
     * -> 특정 횟수에 같은 곳을 방문했다면, 사실 그 뒤에 결과는 똑같음. 따라서 추가적인 탐색을 안해도 된다.
     * -> 근데 사전 순으로 결과가 나와야하니 탐색 순서를 사전 순에 맞게 하면 될 듯?
     * -> dp[y][x][cnt]로 활용해보자.
     */

    static boolean[][][] visited;
    static String result;
    static int[] dy = {1, 0, 0, -1};
    static int[] dx = {0, -1, 1, 0};

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        result = "impossible";
        visited = new boolean[n][m][k + 1];

        dfs(n, m, x-1, y-1, r-1, c-1, k, 1, new StringBuilder());
        return result;
    }

    static void dfs(int n, int m, int y, int x, int c, int r, int k, int cnt, StringBuilder sb) {
        if(cnt == k + 1) return;

        for (int i = 0; i < 4; i++) {
            int nY = y + dy[i];
            int nX = x + dx[i];
            if (outOfRange(nY, nX, n, m)) continue;
            if (!visited[nY][nX][cnt]) {
                visited[nY][nX][cnt] = true;
                if (i == 0) {
                    sb.append("d");
                } else if (i == 1) {
                    sb.append("l");
                } else if (i == 2) {
                    sb.append("r");
                } else if (i == 3) {
                    sb.append("u");
                }
                if (cnt == k && nY == c && nX == r) {
                    result = sb.toString();
                    return;
                }
                dfs(n, m, nY, nX, c, r, k, cnt + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }

    static boolean outOfRange(int y, int x, int n, int m) {
        return y < 0 || x < 0 || y >= n || x >= m;
    }
}
