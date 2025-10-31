package BruteForce.프로그래머스_혼자서하는틱택토;

class Solution {
    // 규칙
    // 1. X의 수는 O의 수보다 같거나 적어야한다.
    // 2. X와 O의 개수 차이는 1 이하여야한다.
    // 3. 둘다 승리할 수 없다.
    // 4. O가 승리했는데, 개수가 서로 같은 경우 탈락
    // 5. X가 승리했는데 O가 많은 경우 탈락
    public int solution(String[] b) {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = b[i].charAt(j);
            }
        }
        int cntO = count('O', board);
        int cntX = count('X', board);
        boolean isOBingo = isBingo('O', board);
        boolean isXBingo = isBingo('X', board);
        if (cntX > cntO) return 0;
        if (Math.abs(cntX - cntO) > 1) return 0;
        if (isOBingo && isXBingo) return 0;
        if (isOBingo && cntO == cntX) return 0;
        if (isXBingo && cntO != cntX) return 0;
        return 1;
    }

    private int count(char type, char[][] board) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == type) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isBingo(char type, char[][] board) {
        loop:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != type) {
                    continue loop;
                }
            }
            return true;
        }

        loop:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[j][i] != type) {
                    continue loop;
                }
            }
            return true;
        }
        if (board[2][0] == type && board[1][1] == type && board[0][2] == type) {
            return true;
        }
        if (board[0][0] == type && board[1][1] == type && board[2][2] == type) {
            return true;
        }
        return false;
    }
}
