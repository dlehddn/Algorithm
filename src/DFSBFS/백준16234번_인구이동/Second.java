package DFSBFS.백준16234번_인구이동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Second {
    static int N, L, R;
    static int[][] countries;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        countries = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                countries[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int result = 0;
        while (true) {
            boolean isEnd = true;
            boolean[][] visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    List<Pair> unions = new ArrayList<>();
                    if (visited[i][j]) continue;
                    unionSearch(i, j, unions, visited);
                    if (unions.size() > 1) {
                        isEnd = false;
                        int sum = 0;
                        for (Pair union : unions) {
                            sum += countries[union.y][union.x];
                        }
                        for (Pair union : unions) {
                            countries[union.y][union.x] = sum / unions.size();
                        }
                    }
                }
            }
            if (isEnd == true) {
                break;
            }
            result++;
        }
        System.out.println(result);
//        for (int[] country : countries) {
//            for (int i : country) {
//                System.out.print(i + " ");
//            }
//            System.out.println();
//        }
    }

    static void unionSearch(int y, int x, List<Pair> unions, boolean[][] visited) {
        Queue<Pair> q = new ArrayDeque<>();
        visited[y][x] = true;
        q.add(new Pair(y, x));

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            unions.add(poll);

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX) || visited[nY][nX]) {
                    continue;
                }
                int pollCnt = countries[poll.y][poll.x];
                int nextCnt = countries[nY][nX];
                if (Math.abs(pollCnt - nextCnt) >= L && Math.abs(pollCnt - nextCnt) <= R) {
                    q.add(new Pair(nY, nX));
                    visited[nY][nX] = true;
                }
            }
        }

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


