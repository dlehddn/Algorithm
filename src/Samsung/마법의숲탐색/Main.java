package Samsung.마법의숲탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C, K, result;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int[][] map;
    static Golem[] golems;
    static boolean[][] visited;
    static boolean isClear;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[R + 3][C];
        golems = new Golem[K + 1];
        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            golems[i] = new Golem(new Pair(1, c - 1), d, i);
        }

        for (int i = 1; i <= K; i++) {
            visited = new boolean[R + 3][C];
            isClear = false;
            gIntoMap(golems[i]);
            step1(golems[i]);
            if (!isClear) {
                step2(golems[i]);
            }
        }

        System.out.println(result);
    }

    static void gIntoMap(Golem golem) {
        map[golem.center.y][golem.center.x] = golem.num;
        for (int i = 0; i < 4; i++) {
            map[golem.center.y + dy[i]][golem.center.x + dx[i]] = golem.num;
        }
    }

    static void step1(Golem golem) {
        visited[golem.center.y][golem.center.x] = true;
        if (canGo(2, golem)) {
            gMove(2, golem);
            step1(golem);
        } else if (canGo(3, golem)) {
            gMove(3, golem);
            rotate(3, golem);
            step1(golem);
        } else if (canGo(1, golem)) {
            gMove(1, golem);
            rotate(1, golem);
            step1(golem);
        } else {
            if (isOut(golem)) {
                clearMap();
                isClear = true;
            }
        }
    }

    static void gMove(int d, Golem golem) {
        map[golem.center.y][golem.center.x] = 0;
        for (int i = 0; i < 4; i++) {
            map[golem.center.y + dy[i]][golem.center.x + dx[i]] = 0;
        }

        map[golem.center.y + dy[d]][golem.center.x + dx[d]] = golem.num;
        for (int i = 0; i < 4; i++) {
            map[golem.center.y + dy[i] + dy[d]][golem.center.x + dx[i] + dx[d]] = golem.num;
        }
        golem.center = new Pair(golem.center.y + dy[d], golem.center.x + dx[d]);
    }

    static void rotate(int d, Golem golem) {
        int[][] mini = new int[3][3];
        copy3X3(golem, mini);
        int[][] copied = deepCopy(mini);
        if (d == 1) {
            copied[1][0] = mini[2][1];
            copied[1][2] = mini[0][1];
            copied[0][1] = mini[1][0];
            copied[2][1] = mini[1][2];
            golem.exit = (golem.exit + 1) % 4;
        } else {
            copied[1][0] = mini[0][1];
            copied[1][2] = mini[2][1];
            copied[0][1] = mini[1][2];
            copied[2][1] = mini[1][0];
            golem.exit = (golem.exit + 3) % 4;
        }
        map[golem.center.y - 1][golem.center.x] = copied[0][1];
        map[golem.center.y + 1][golem.center.x] = copied[2][1];
        map[golem.center.y][golem.center.x - 1] = copied[1][0];
        map[golem.center.y][golem.center.x + 1] = copied[1][2];
    }

    static void copy3X3(Golem golem, int[][] mini) {
        mini[1][1] = map[golem.center.y][golem.center.x];
        mini[1][0] = map[golem.center.y][golem.center.x - 1];
        mini[1][2] = map[golem.center.y][golem.center.x + 1];
        mini[0][1] = map[golem.center.y - 1][golem.center.x];
        mini[2][1] = map[golem.center.y + 1][golem.center.x];
    }

    static int[][] deepCopy(int[][] origin) {
        int[][] copied = new int[origin.length][origin[0].length];
        for (int i = 0; i < origin.length; i++) {
            for (int j = 0; j < origin[0].length; j++) {
                copied[i][j] = origin[i][j];
            }
        }
        return copied;
    }

    static boolean canGo(int d, Golem golem) {
        if (visited[golem.center.y + dy[d]][golem.center.x + dx[d]]) return false;
        if (outOfRange(golem.center.y + 2, golem.center.x)) return false;
        if (d == 2) {
            if (map[golem.center.y + 2][golem.center.x] != 0 || map[golem.center.y + 1][golem.center.x - 1] != 0 || map[golem.center.y + 1][golem.center.x + 1] != 0)
                return false;
        } else if (d == 3) {
            if (outOfRange(golem.center.y, golem.center.x - 2)) return false;
            if (map[golem.center.y][golem.center.x - 2] != 0 || map[golem.center.y - 1][golem.center.x - 1] != 0
                    || map[golem.center.y + 1][golem.center.x - 1] != 0 || map[golem.center.y + 1][golem.center.x - 2] != 0 || map[golem.center.y + 2][golem.center.x - 1] != 0)
                return false;
        } else {
            if (outOfRange(golem.center.y, golem.center.x + 2)) return false;
            if (map[golem.center.y][golem.center.x + 2] != 0 || map[golem.center.y - 1][golem.center.x + 1] != 0
                    || map[golem.center.y + 1][golem.center.x + 1] != 0 || map[golem.center.y + 1][golem.center.x + 2] != 0 || map[golem.center.y + 2][golem.center.x + 1] != 0)
                return false;
        }
        return true;
    }

    static boolean isOut(Golem golem) {
        return golem.center.y >= 4 ? false : true;
    }

    static void clearMap() {
        map = new int[R + 3][C];
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= R + 3 || x >= C;
    }

    static void step2(Golem golem) {
        boolean[][] visited = new boolean[R + 3][C];
        int max = Integer.MIN_VALUE;
        Queue<Fairy> q = new LinkedList<>();
        q.add(new Fairy(golem.center, golem.num));
        visited[golem.center.y][golem.center.x] = true;

        while (!q.isEmpty()) {
            Fairy poll = q.poll();

            if (poll.position.y > max) {
                max = poll.position.y;
            }

            for (int i = 0; i < 4; i++) {
                int nY = poll.position.y + dy[i];
                int nX = poll.position.x + dx[i];
                if (outOfRange(nY, nX)) continue;
                if (visited[nY][nX]) continue;
                if (map[nY][nX] == 0) continue;

                Golem curG = golems[poll.gNum];
                if (poll.gNum == map[nY][nX]) {
                    q.add(new Fairy(new Pair(nY, nX), poll.gNum));
                    visited[nY][nX] = true;
                } else if (poll.gNum != map[nY][nX] && poll.position.y == curG.center.y + dy[curG.exit] && poll.position.x == curG.center.x + dx[curG.exit]) {
                    q.add(new Fairy(new Pair(nY, nX), map[nY][nX]));
                    visited[nY][nX] = true;
                }
            }
        }
        result += max - 2;
    }

    static class Golem {
        Pair center;
        int exit, num;

        public Golem(Pair center, int exit, int num) {
            this.center = center;
            this.exit = exit;
            this.num = num;
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Fairy {
        Pair position;
        int gNum;

        public Fairy(Pair position, int gNum) {
            this.position = position;
            this.gNum = gNum;
        }
    }
}
