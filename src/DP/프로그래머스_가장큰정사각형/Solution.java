package DP.프로그래머스_가장큰정사각형;

class Solution {
    private int[] dy = {0, -1, -1};
    private int[] dx = {-1, 0, -1};

    public int solution(int[][] board) {
        int[][] dp = new int[board.length][board[0].length];

        int answer = 0;
        for (int i = 0; i < board.length; i++) {
            loop:
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 1) {
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    int nY = i + dy[k];
                    int nX = j + dx[k];
                    if (outOfRange(nY, nX, board.length, board[0].length)) {
                        dp[i][j] = 1;
                        answer = Math.max(answer, 1);
                        continue loop;
                    }
                    min = Math.min(min, dp[nY][nX]);
                }
                dp[i][j] = min == Integer.MAX_VALUE ? 0 : min + 1;
                answer = Math.max(answer, dp[i][j]);
            }
        }

        return answer * answer;
    }

    private boolean outOfRange(int y, int x, int ySize, int xSize) {
        return y < 0 || x < 0 || y >= ySize || x >= xSize;
    }
}
