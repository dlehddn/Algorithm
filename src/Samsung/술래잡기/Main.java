package Samsung.술래잡기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static ArrayList<Integer>[][] humanMap;
    static ArrayList<Integer>[][] nextHumanMap;
    static Human attacker;
    static int[][] treeMap;
    static int N, M, H, K;
    static int[] dx = {0, 1, 0, -1}; //상 우 하 좌
    static int[] dy = {-1, 0, 1, 0};
    static int lineCnt, remainCnt;
    static boolean isReverse;
    static boolean[][] reverseVisited;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        humanMap = new ArrayList[N][N];
        nextHumanMap = new ArrayList[N][N];
        treeMap = new int[N][N];
        reverseVisited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                humanMap[i][j] = new ArrayList<>();
                nextHumanMap[i][j] = new ArrayList<>();
            }
        }
        attacker = new Human(N / 2, N / 2, 0);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            humanMap[y][x].add(d);
        }

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            treeMap[y][x] = 1;
        }

        int turn = 1;
        while (turn <= K) {
            step1_Move();
            step2_attack(turn);
            turn++;
        }
        System.out.println(result);
    }

    static boolean checkDistance(int y, int x) {
        if (Math.abs(x - attacker.x) + Math.abs(y - attacker.y) <= 3) {
            return true;
        }
        return false;
    }

    static void step1_Move() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (humanMap[i][j].size() > 0) {
                    for (int k = 0; k < humanMap[i][j].size(); k++) {
                        int curDir = humanMap[i][j].get(k);
                        int nextX = j + dx[curDir];
                        int nextY = i + dy[curDir];
                        if (!checkDistance(i, j)) {
                            nextHumanMap[i][j].add(curDir);
                            continue;
                        }
                        if (nextY < 0 || nextX < 0 || nextX >= N || nextY >= N) {
                            curDir = (curDir + 2) % 4;
                            nextX = j + dx[curDir];
                            nextY = i + dy[curDir];
                        }
                        if (nextY == attacker.y && nextX == attacker.x) {
                            nextHumanMap[i][j].add(curDir);
                        } else {
                            nextHumanMap[nextY][nextX].add(curDir);
                        }

                    }
                } else {
                    for (Integer integer : humanMap[i][j]) {
                        nextHumanMap[i][j].add(integer);
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                humanMap[i][j].clear();
                for (Integer integer : nextHumanMap[i][j]) {
                    humanMap[i][j].add(integer);
                }
                nextHumanMap[i][j].clear();
            }
        }
    }

    static void step2_attack(int curTurn) {
        if (!isReverse) {
            roundDir();
        } else {
            reverseDir();
        }
        /// 여기까지가 술래 한칸 움직이고 필요 시 방향 전환까지, 이후에는 술래 잡아먹는거 작성

        int cnt = 0;
        for (int i = 0; i < 3; i++) {
            int nextY = attacker.y + i * dy[attacker.dir];
            int nextX = attacker.x + i * dx[attacker.dir];
            try {
                if (humanMap[nextY][nextX].size() > 0 && treeMap[nextY][nextX] == 0) {
                    cnt += humanMap[nextY][nextX].size();
                    humanMap[nextY][nextX] = new ArrayList<>();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }
        result += curTurn * cnt;
    }

    static void roundDir() {

        if (remainCnt == 0) {
            remainCnt = lineCnt / 2 + 1;
        }

        remainCnt--;
        attacker.x += dx[attacker.dir];
        attacker.y += dy[attacker.dir];

        if (remainCnt == 0) {
            lineCnt++;
            attacker.dir = lineCnt % 4;
        }

        if (attacker.x == 0 && attacker.y == 0) {
            lineCnt = 0;
            remainCnt = 0;
            isReverse = true;
            attacker.dir = 2;
            reverseVisited = new boolean[N][N];
            reverseVisited[0][0] = true;
        }
    }

    static void reverseDir() {
        attacker.x += dx[attacker.dir];
        attacker.y += dy[attacker.dir];
        reverseVisited[attacker.y][attacker.x] = true;

        if (attacker.x == N / 2 && attacker.y == N / 2) {
            attacker.dir = 0;
            isReverse = false;
            return;
        }

        int nextY = attacker.y + dy[attacker.dir];
        int nextX = attacker.x + dx[attacker.dir];

        if (nextY < 0 || nextX < 0 || nextX >= N || nextY >= N || reverseVisited[nextY][nextX]) {
            attacker.dir = (attacker.dir + 3) % 4;
        }
    }

    static class Human {
        int y;
        int x;
        int dir;

        public Human(int y, int x, int dir) {
            this.y = y;
            this.x = x;
            this.dir = dir;
        }
    }

}
