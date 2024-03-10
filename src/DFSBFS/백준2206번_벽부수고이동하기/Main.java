package DFSBFS.백준2206번_벽부수고이동하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    /**
     * 방문처리를 3차원으로 하는게 핵심,
     * 현재 자리에 벽을 뚫고 왔는지 or 한번도 안뚫고 왔는지 체크
     */

    static int[][] map;
    static int N, M, result;
    static boolean[][][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        result = -1;
        bfs();
        System.out.println(result);

    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, false));


        while (!q.isEmpty()) {
            Node poll = q.poll();
            if (poll.y == N - 1 && poll.x == M - 1) {
                result = poll.cnt;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if (!visited[nY][nX][poll.isWallPass == true ? 1 : 0] && map[nY][nX] == 0) {
                    visited[nY][nX][poll.isWallPass == true ? 1 : 0] = true;
                    q.add(new Node(nY, nX, poll.cnt + 1, poll.isWallPass));
                }
                if (!visited[nY][nX][1] && map[nY][nX] == 1 && poll.isWallPass == false) {
                    visited[nY][nX][1] = true;
                    q.add(new Node(nY, nX, poll.cnt + 1, true));
                }
            }
        }
    }

    static class Node {
        int y, x, cnt;
        boolean isWallPass;

        public Node(int y, int x, boolean isWallPass) {
            this.y = y;
            this.x = x;
            this.isWallPass = isWallPass;
        }

        public Node(int y, int x, int cnt, boolean isWallPass) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.isWallPass = isWallPass;
        }
    }
}
