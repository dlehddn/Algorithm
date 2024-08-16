package DFSBFS.프로그래머스_아이템줍기;

import java.util.*;
class Solution {
    /**
    * 1. bfs 탐색을 위한 canGo 배열 생성 -> boolean[][]
    * 2. 각 사각형 돌면서 일단 모든 사각형 테두리 true로 변경
    * 3. 다시 하나씩 돌면서 내부에 있는 true -> false로 변경
    * 4. 준비 끝, bfs()
    */

    static boolean[][] canGo;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        canGo = new boolean[101][101];
        for (int[] r : rectangle) {
            fill(r);
        }
        for (int[] r : rectangle) {
            removeInner(r);
        }
        return bfs(characterX, characterY, itemX, itemY);

    }

    static int bfs(int cX, int cY, int iX, int iY) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        q.add(new Pair(cY * 2, cX * 2, 0));
        visited[cY * 2][cX * 2] = true;

        while(!q.isEmpty()) {
            Pair poll = q.poll();

            if (poll.y == 2 * iY && poll.x == 2 * iX) {
                return poll.cnt / 2;
            }

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (!outOfRange(nY, nX) && !visited[nY][nX] && canGo[nY][nX] == true) {
                    q.add(new Pair(nY, nX, poll.cnt + 1));
                    visited[nY][nX] = true;
                }
            }
        }
        return 0;
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= 101 || x >= 101;
    }


    static void removeInner(int[] rec) {
        for (int i = 2 * rec[1] + 1; i <= 2 * rec[3] - 1; i++) {
            for (int j = 2 * rec[0] + 1; j <= 2 * rec[2] - 1; j++) {
                canGo[i][j] = false;
            }
        }
    }

    static void fill(int[] rec) {
        for (int i = 2 * rec[0]; i <= 2 * rec[2]; i++) {
            canGo[2 * rec[1]][i] = true;
            canGo[2 * rec[3]][i] = true;
        }
        for (int i = 2 * rec[1]; i <= 2 * rec[3]; i++) {
            canGo[i][2 * rec[0]] = true;
            canGo[i][2 * rec[2]] = true;
        }
    }

    static class Pair {
        int y, x, cnt;

        public Pair(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}