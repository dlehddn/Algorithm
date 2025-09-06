package DFSBFS.백준16724번_피리부는사나이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};
    static List<Character> directions = Arrays.asList('D', 'R', 'U', 'L');
    static int N, M;
    static char[][] map;
    static int[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int result = 0;
        int seq = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    result += run(i, j, seq);
                    seq++;
                }
            }
        }
        System.out.println(result);
    }

    static int run(int y, int x, int seq) {
        int dir = directions.indexOf(map[y][x]);
        int nY = y + dy[dir];
        int nX = x + dx[dir];

        if (visited[nY][nX] != 0 && visited[nY][nX] != seq) {
            visited[y][x] = visited[nY][nX];
            return 0;
        }
        if (visited[nY][nX] == 0) {
            visited[y][x] = seq;
            return run(nY, nX, seq);
        }
        if (visited[nY][nX] == seq) {
            visited[y][x] = seq;
            return 1;
        }
        return 1;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
