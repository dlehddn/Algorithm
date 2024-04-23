package DFSBFS.벽부수고이동하기_다시풀기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());

    }

    static int bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][2];
        q.add(new Node(0, 0, 1, false));
        visited[0][0][0] = true;
        int result = 0;

        while (!q.isEmpty()) {
            Node poll = q.poll();
            // N-1, M-1 일 때

            if (poll.y == N - 1 && poll.x == M - 1) {
                result = poll.cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfBounds(nY, nX)) continue;
                if (map[nY][nX] == 0 && !visited[nY][nX][poll.crashed == true ? 1 : 0]) {
                    q.add(new Node(nY, nX, poll.cnt + 1, poll.crashed));
                    visited[nY][nX][poll.crashed == true ? 1 : 0] = true;
                } else if (map[nY][nX] == 1 && !poll.crashed && !visited[nY][nX][1]) {
                    q.add(new Node(nY, nX, poll.cnt + 1, true));
                    visited[nY][nX][1] = true;
                }
            }
        }

        return result == 0 ? -1 : result;
    }

    static boolean outOfBounds(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static class Node {
        int y, x, cnt;
        boolean crashed;

        public Node(int y, int x, int cnt, boolean crashed) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.crashed = crashed;
        }
    }
}
