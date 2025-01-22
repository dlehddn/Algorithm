package DFSBFS.백준16933번_벽부수고이동하기3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Third {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static final int NIGHT = 1;
    static final int AFTERNOON = 0;
    static final int NON_FINISHED = -1;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K + 1];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        Queue<Movement> q = new LinkedList<>();
        q.add(new Movement(0, 0, 1, K, AFTERNOON));
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            Movement poll = q.poll();

            if (isArrived(poll)) {
                System.out.println(poll.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX)) continue;
                if (map[nY][nX] == 0 && !visited[nY][nX][poll.remain]) {
                    q.add(new Movement(nX, nY, poll.cnt + 1, poll.remain, reverse(poll)));
                    visited[nY][nX][poll.remain] = true;
                } else if (map[nY][nX] == 1 && poll.state == AFTERNOON && poll.remain > 0 && !visited[nY][nX][poll.remain - 1]) {
                    q.add(new Movement(nX, nY, poll.cnt + 1, poll.remain - 1, reverse(poll)));
                    visited[nY][nX][poll.remain - 1] = true;
                } else if (map[nY][nX] == 1 && poll.remain > 0) {
                    q.add(new Movement(poll.x, poll.y, poll.cnt + 1, poll.remain, reverse(poll)));
                    visited[poll.y][poll.x][poll.remain] = true;
                }
            }
        }
        System.out.println(NON_FINISHED);
    }

    private static int reverse(Movement poll) {
        return poll.state == AFTERNOON ? NIGHT : AFTERNOON;
    }

    private static boolean outOfRange(int nY, int nX) {
        return nY < 0 || nX < 0 || nY >= N || nX >= M;
    }

    private static boolean isArrived(Movement poll) {
        return poll.y == N - 1 && poll.x == M - 1;
    }

    static class Movement {
        int x, y, cnt, remain, state;

        public Movement(int x, int y, int cnt, int remain, int state) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.remain = remain;
            this.state = state;
        }
    }
}
