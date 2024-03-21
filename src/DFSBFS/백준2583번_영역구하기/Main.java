package DFSBFS.백준2583번_영역구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> result;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        result = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int sX = Integer.parseInt(st.nextToken());
            int sY = Integer.parseInt(st.nextToken());
            int eX = Integer.parseInt(st.nextToken()) - 1;
            int eY = Integer.parseInt(st.nextToken()) - 1;
            for (int j = sY; j <= eY; j++) {
                for (int k = sX; k <= eX; k++) {
                    map[j][k] = 1;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && !visited[i][j]) {
                    bfs(i, j);
                }
            }
        }

        Collections.sort(result);
        System.out.println(result.size());
        for (Integer integer : result) {
            System.out.print(integer + " ");
        }
    }

    private static void bfs(int y, int x) {
        Queue<Pair> q = new LinkedList<>();
        int cnt = 1;
        q.add(new Pair(y, x));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Pair poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if (map[nY][nX] == 0 && !visited[nY][nX]) {
                    cnt++;
                    q.add(new Pair(nY, nX));
                    visited[nY][nX] = true;
                }
            }

        }
        result.add(cnt);
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

}
