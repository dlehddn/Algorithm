package Samsung.꼬리잡기놀이;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] map;
    static ArrayList<Pair> head = new ArrayList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Pair start;
    static int totalScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    head.add(new Pair(i, j));
                }
            }
        }
//
        int round = 0;
        while (round < K) {
            step1_MoveAll();
            step2_getScore(round);
            round++;
        }
        System.out.println(totalScore);


        ///// Test Zone //////
//        step1_MoveAll();
//        step2_getScore(0);
//
//        int round = 0;
//        while (round < K) {
//            step1_MoveAll();
//            step2_getScore(round);
//            round++;
//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }
//                System.out.println(totalScore);
//                for (Pair pair : head) {
//                    System.out.println("head = (" + pair.y + ", " + pair.x + ")");
//                }
//
//        }


//

//
//        System.out.println("start = (" + start.y + ", " + start.x + ")");
//        System.out.println("totalScore = " + totalScore);
        ////////////////////////


    }

    static void step1_MoveAll() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp[i][j] = map[i][j];
            }
        }
        for (int i = 0; i < M; i++) {
            Pair curHead = head.get(i);
            Queue<Pair> q = new LinkedList<>();
            q.add(new Pair(curHead.y, curHead.x));
            boolean[][] visited = new boolean[N][N];
            visited[curHead.y][curHead.x] = true;
            while (!q.isEmpty()) {
                Pair poll = q.poll();
                for (int j = 0; j < 4; j++) {
                    int nextY = poll.y + dy[j];
                    int nextX = poll.x + dx[j];
                    if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
                        continue;
                    }
                    if (map[poll.y][poll.x] == 1) {
                        if (map[nextY][nextX] == 4 || map[nextY][nextX] == 3) {
                            tmp[nextY][nextX] = 1;
                            head.set(i, new Pair(nextY, nextX));
                        }
                    } else if (map[poll.y][poll.x] == 2) {
                        if (map[nextY][nextX] == 1) {
                            tmp[nextY][nextX] = 2;
                        } else if (map[nextY][nextX] == 2) {
                            tmp[nextY][nextX] = 2;
                        }
                    } else if (map[poll.y][poll.x] == 3) {
                        if (map[nextY][nextX] == 2) {
                            tmp[nextY][nextX] = 3;
                        }
                    } else if (map[poll.y][poll.x] == 4) {
                        if (map[nextY][nextX] == 3) {
                            tmp[nextY][nextX] = 4;
                        }
                    }
                }
                for (int j = 0; j < 4; j++) {
                    int nextY = poll.y + dy[j];
                    int nextX = poll.x + dx[j];
                    if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
                        continue;
                    }
                    if (map[poll.y][poll.x] == 1) {
                        if (map[nextY][nextX] == 2 && !visited[nextY][nextX]) {
                            q.add(new Pair(nextY, nextX));
                            visited[nextY][nextX] = true;
                        }
                    } else if (map[poll.y][poll.x] == 2) {
                        if (map[nextY][nextX] == 3 && !visited[nextY][nextX]) {
                            q.add(new Pair(nextY, nextX));
                            visited[nextY][nextX] = true;
                        } else if (map[nextY][nextX] == 2 && !visited[nextY][nextX]) {
                            q.add(new Pair(nextY, nextX));
                            visited[nextY][nextX] = true;
                        }
                    } else if (map[poll.y][poll.x] == 3) {
                        if (map[nextY][nextX] == 4 && !visited[nextY][nextX]) {
                            q.add(new Pair(nextY, nextX));
                            visited[nextY][nextX] = true;
                        } else if (map[nextY][nextX] == 1 && !visited[nextY][nextX]) {
                            q.add(new Pair(nextY, nextX));
                            visited[nextY][nextX] = true;
                        }
                    }
                }
            }
        }
        map = tmp;
    }

    static void step2_getScore(int curRound) {
        findStart(curRound);
        throwBall(curRound);
    }

    static void findStart(int curRound) {
        if ((curRound / N) % 4 == 0) {
            start = new Pair(curRound % N, 0);
        } else if ((curRound / N) % 4 == 1) {
            start = new Pair(N - 1, curRound % N);
        } else if ((curRound / N) % 4 == 2) {
            start = new Pair((N - 1) - curRound % N, N - 1);
        } else if ((curRound / N) % 4 == 3) {
            start = new Pair(0, (N - 1) - curRound % N);
        }
    }

    static void throwBall(int curRound) {
        for (int i = 0; i < N; i++) {
            int nextY = start.y + i * dy[(curRound / N) % 4];
            int nextX = start.x + i * dx[(curRound / N) % 4];
            if (map[nextY][nextX] == 1) {
                totalScore += 1;
                changeDir(nextY, nextX);
                break;
            } else if (map[nextY][nextX] == 2 || map[nextY][nextX] == 3) {
                int count = cntBfs(nextY, nextX);
                totalScore += count * count;
                changeDir(nextY, nextX);
                break;
            }
        }
    }

    static void changeDir(int y, int x) {
        int curGID = 0;
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Pair(y, x));
        visited[y][x] = true;
        Pair tmp = null;

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            if (map[poll.y][poll.x] == 1) {
                map[poll.y][poll.x] = 3;
                for (int i = 0; i < head.size(); i++) {
                    Pair pair = head.get(i);
                    if (pair.x == poll.x && pair.y == poll.y) {
                        curGID = i;
                        break;
                    }
                }
            } else if (map[poll.y][poll.x] == 3) {
                map[poll.y][poll.x] = 1;
                tmp = new Pair(poll.y, poll.x);
            }
            for (int i = 0; i < 4; i++) {
                int nextY = poll.y + dy[i];
                int nextX = poll.x + dx[i];
                if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
                    continue;
                }
                if (!visited[nextY][nextX]) {
                    if (map[nextY][nextX] == 1 || map[nextY][nextX] == 2 || map[nextY][nextX] == 3) {
                        q.add(new Pair(nextY, nextX));
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }
        head.set(curGID, new Pair(tmp.y, tmp.x));
    }

    static int cntBfs(int y, int x) {
        int[][] count = new int[N][N];
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Pair(y, x));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = poll.y + dy[i];
                int nextX = poll.x + dx[i];
                if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
                    continue;
                }
                if (map[poll.y][poll.x] == 3 && map[nextY][nextX] == 1) {
                    continue;
                }
                if (!visited[nextY][nextX]) {
                    if (map[nextY][nextX] == 1 || map[nextY][nextX] == 2) {
                        q.add(new Pair(nextY, nextX));
                        count[nextY][nextX] = count[poll.y][poll.x] + 1;
                        visited[nextY][nextX] = true;
                    }
                    if (map[nextY][nextX] == 1) {
                        return count[nextY][nextX] + 1;
                    }
                }
            }
        }
        return 0;
    }


    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}