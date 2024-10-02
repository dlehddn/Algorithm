package DFSBFS.백준16234번_인구이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, R;
    static int[][] map;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            if (!isMoved()) break;
            day++;
        }
        System.out.println(day);
    }

    static boolean isMoved() {
        boolean[][] visited = new boolean[N][N];
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int unionCnt = bfs(new Pair(i, j), visited);
                if (unionCnt > 1) flag = true;
            }
        }
        return flag;
    }

    static int bfs(Pair start, boolean[][] visited) {
        Queue<Pair> q = new LinkedList<>();
        List<Pair> union = new ArrayList<>();
        union.add(start);

        int unionCnt = 1;
        int sum = map[start.y][start.x];

        if (!visited[start.y][start.x]) {
            q.add(start);
            visited[start.y][start.x] = true;
        }

        while (!q.isEmpty()) {
            Pair poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX) || visited[nY][nX]) continue;

                int diff = Math.abs(map[poll.y][poll.x] - map[nY][nX]);
                if (diff >= L && diff <= R) {
                    q.add(new Pair(nY, nX));
                    visited[nY][nX] = true;
                    unionCnt++;
                    sum += map[nY][nX];
                    union.add(new Pair(nY, nX));
                }
            }

        }
        int balance = sum / unionCnt;
        for (Pair pair : union) {
            map[pair.y][pair.x] = balance;
        }
        return unionCnt;
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
