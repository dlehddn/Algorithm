package Comb_Per.백준15686번_치킨배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, min;
    static int[][] map;
    static List<Pair> chickens;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        chickens = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if (num == 2) {
                    chickens.add(new Pair(i, j));
                }
            }
        }

        min = Integer.MAX_VALUE;
        comb(0, -1, new Pair[M]);
        System.out.println(min);

    }

    static void comb(int cnt, int previous, Pair[] picks) {
        if (cnt == M) {
            checkDistance(picks);
            return;
        }

        for (int i = previous + 1; i < chickens.size(); i++) {
            picks[cnt] = chickens.get(i);
            comb(cnt + 1, i, picks);
        }
    }

    private static void checkDistance(Pair[] picks) {
        int[][] sumArr = new int[N][N];
        int sum = 0;

        for (Pair pick : picks) {
            bfs(pick, sumArr);
        }

        for (int[] ints : sumArr) {
            for (int num : ints) {
                if (num != 0) {
                    sum += num;
                }
            }
        }

        if (sum < min) {
            min = sum;
        }
    }

    private static void bfs(Pair pick, int[][] sumArr) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        visited[pick.y][pick.x] = true;
        q.add(new Node(pick.y, pick.x, 0));

        while (!q.isEmpty()) {
            Node poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if(nY < 0 || nX < 0 || nY >= N || nX >= N) continue;

                if (map[nY][nX] != 1 && !visited[nY][nX]) {
                    q.add(new Node(nY, nX, poll.cnt + 1));
                    visited[nY][nX] = true;
                } else if (map[nY][nX] == 1 && !visited[nY][nX]) {
                    q.add(new Node(nY, nX, poll.cnt + 1));
                    visited[nY][nX] = true;
                    sumArr[nY][nX] = sumArr[nY][nX] != 0 ? Math.min(sumArr[nY][nX], poll.cnt + 1) : poll.cnt + 1;
                }
            }
        }


    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Node {
        int y, x, cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
