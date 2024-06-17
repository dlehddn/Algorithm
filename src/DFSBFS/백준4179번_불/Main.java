package DFSBFS.백준4179번_불;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static boolean[][] fired, visited;
    static int[][] map;
    static int R, C;
    static Queue<Pair> firedQ;
    static Queue<Jihun> visitedQ;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy= {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        fired = new boolean[R][C];
        visited = new boolean[R][C];
        firedQ = new LinkedList<>();
        visitedQ = new LinkedList<>();


        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                if (c == '#') {
                    map[i][j] = 1;
                } else if (c == 'J') {
                    visitedQ.add(new Jihun(i, j, 1));
                    visited[i][j] = true;
                } else if (c == 'F'){
                    fired[i][j] = true;
                    firedQ.add(new Pair(i, j));
                }
            }
        }

        while (!firedQ.isEmpty() || !visitedQ.isEmpty()) {
            fire();
            int n = run();
            if (n != 0) {
                System.out.println(n);
                return;
            }
        }
        System.out.println("IMPOSSIBLE");

    }

    static int run() {
        int size = visitedQ.size();
        while (size > 0) {
            Jihun poll = visitedQ.poll();

            if (poll.y == 0 || poll.x == 0 || poll.y == R - 1 || poll.x == C - 1) {
                return poll.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];

                if (nY >= 0 && nX >= 0 && nY < R && nX < C && map[nY][nX] == 0 && !fired[nY][nX] && !visited[nY][nX]) {
                    visited[nY][nX] = true;
                    visitedQ.add(new Jihun(nY, nX, poll.cnt + 1));
                }
            }
            size--;
        }
        return 0;
    }


    static void fire() {
        int size = firedQ.size();
        while (size > 0) {
            Pair poll = firedQ.poll();

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];

                if (nY >= 0 && nX >= 0 && nY < R && nX < C && map[nY][nX] == 0 && !fired[nY][nX]) {
                    fired[nY][nX] = true;
                    firedQ.add(new Pair(nY, nX));
                }
            }
            size--;
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Jihun {
        int y, x, cnt;

        public Jihun(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
