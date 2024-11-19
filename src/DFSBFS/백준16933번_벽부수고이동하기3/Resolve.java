package DFSBFS.백준16933번_벽부수고이동하기3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Resolve {

    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;
    static Queue<Block> q = new LinkedList();
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

        bfs();

    }

    static void bfs() {
        q.add(new Block(0, 0, 1, 0, true));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Block poll = q.poll();

            if (poll.y == N - 1 && poll.x == M - 1) {
                System.out.println(poll.goCnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX)) {
                    continue;
                }

                if (map[nY][nX] == 0 && !visited[nY][nX][poll.boomCnt]) {
                    visited[nY][nX][poll.boomCnt] = true;
                    q.add(new Block(nY, nX, poll.goCnt + 1, poll.boomCnt, poll.isAfternoon ? false : true));
                }

                if (map[nY][nX] == 1 && poll.isAfternoon && poll.boomCnt < K && !visited[nY][nX][poll.boomCnt + 1]) {
                    visited[nY][nX][poll.boomCnt + 1] = true;
                    q.add(new Block(nY, nX, poll.goCnt + 1, poll.boomCnt + 1, false));
                } else if (map[nY][nX] == 1 && !poll.isAfternoon && poll.boomCnt < K) {
                    q.add(new Block(poll.y, poll.x, poll.goCnt + 1, poll.boomCnt, true));
                }
            }
        }

        System.out.println(-1);
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static class Block {
        int y, x, goCnt, boomCnt;
        boolean isAfternoon;

        public Block(int y, int x, int goCnt, int boomCnt, boolean isAfternoon) {
            this.y = y;
            this.x = x;
            this.goCnt = goCnt;
            this.boomCnt = boomCnt;
            this.isAfternoon = isAfternoon;
        }
    }
}
