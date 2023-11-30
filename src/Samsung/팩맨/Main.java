package Samsung.팩맨;

import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {
    /**
     * 중요!! 디버깅 한 곳 체크
     * 1. 3칸 움직이면서 상, 좌, 하, 우 순서로 우선순위를 매길 때 -> 순서대로 탐색이 아니라 역순으로 탐색해야 마지막에 우선순위가 가장 높았던 애가 선택된다!
     * 2. 2턴이 지나면 시체가 삭제되는 상황에서, 단순하게 +=, -=로 계산하는게 아니라 누적합을 담는 애, 해당 턴에 추가된 양을 담는 애를 따로 구분해서 관리해야 계산이 맞다!!!!!!!!
     */
    static ArrayList<Integer>[][] originMonster = new ArrayList[4][4];
    static ArrayList<Integer>[][] copyMonster = new ArrayList[4][4];
    static int M, T;
    static int[] dx8 = {0, -1, -1, -1, 0, 1, 1, 1};  // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dy8 = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx4 = {0, -1, 0, 1};
    static int[] dy4 = {-1, 0, 1, 0};
    static int[][][] diedMonster;
    static int[][][] diedMonsterCnt;
    static Pair pack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        diedMonster = new int[4][4][T];
        diedMonsterCnt = new int[4][4][T];
        st = new StringTokenizer(br.readLine());
        pack = new Pair(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                originMonster[i][j] = new ArrayList<>();
                copyMonster[i][j] = new ArrayList<>();
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            originMonster[y][x].add(d);
        }
        int turn = 0;
        while (turn < T) {
            if (turn >= 1) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        diedMonster[i][j][turn] = diedMonster[i][j][turn - 1];
                    }
                }
            }
            step1_CopyMonster();
            step2_MoveMonster(turn);
            step3_EatMonster(turn);
            step4_RemoveDiedMonster(turn);
            step5_RealCopy();
            turn++;
        }
        System.out.println(getResult());
    }

    static void step1_CopyMonster() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (Integer dir : originMonster[i][j]) {
                    copyMonster[i][j].add(dir);
                }
            }
        }
    }

    static void step2_MoveMonster(int curTurn) {
        ArrayList<Integer>[][] tmp = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmp[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (originMonster[i][j].size() > 0) {
                    for (Integer curMonDir : originMonster[i][j]) {
                        boolean ok = false;
                        for (int k = 0; k < 8; k++) {
                            int nextY = i + dy8[(curMonDir + k) % 8];
                            int nextX = j + dx8[(curMonDir + k) % 8];
                            if (canGo(curTurn, nextY, nextX)) {
                                tmp[nextY][nextX].add((curMonDir + k) % 8);
                                ok = true;
                                break;
                            }
                        }
                        if (!ok) {
                            tmp[i][j].add(curMonDir);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                originMonster[i][j].clear();
                for (Integer integer : tmp[i][j]) {
                    originMonster[i][j].add(integer);
                }
            }
        }
    }

    static boolean canGo(int curTurn, int y, int x) {
        if (y < 0 || x < 0 || y >= 4 || x >= 4) {
            return false;
        }
        if (y == pack.y && x == pack.x) {
            return false;
        }
        if (diedMonster[y][x][curTurn] > 0) {
            return false;
        }
        return true;
    }

    static void step3_EatMonster(int curTurn) {
        int maxEat = Integer.MIN_VALUE;
        int[] routes = new int[2];
        Pair arrive = new Pair(-1, -1);
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                for (int k = 3; k >= 0; k--) {
                    int firstY = pack.y + dy4[i];
                    int firstX = pack.x + dx4[i];
                    if (firstX < 0 || firstY < 0 || firstX >= 4 || firstY >= 4) {
                        continue;
                    }
                    int secondY = firstY + dy4[j];
                    int secondX = firstX + dx4[j];
                    if (secondX < 0 || secondY < 0 || secondX >= 4 || secondY >= 4) {
                        continue;
                    }
                    int arriveY = secondY + dy4[k];
                    int arriveX = secondX + dx4[k];
                    if (arriveX < 0 || arriveY < 0 || arriveX >= 4 || arriveY >= 4) {
                        continue;
                    }
                    int tmpSize = originMonster[firstY][firstX].size() + originMonster[secondY][secondX].size();
                    if (firstX != arriveX || firstY != arriveY) {
                        tmpSize += originMonster[arriveY][arriveX].size();
                    }

                    if (tmpSize >= maxEat) {
                        maxEat = tmpSize;
                        routes[0] = i;
                        routes[1] = j;
                        arrive.y = arriveY;
                        arrive.x = arriveX;
                    }
                }
            }
        }
        Pair first = new Pair(pack.y + dy4[routes[0]], pack.x + dx4[routes[0]]);
        Pair second = new Pair(first.y + dy4[routes[1]], first.x + dx4[routes[1]]);

        pack.y = arrive.y;
        pack.x = arrive.x;
        diedMonster[pack.y][pack.x][curTurn] += originMonster[pack.y][pack.x].size();
        diedMonsterCnt[pack.y][pack.x][curTurn] += originMonster[pack.y][pack.x].size();
        originMonster[pack.y][pack.x] = new ArrayList<>();

        diedMonster[first.y][first.x][curTurn] += originMonster[first.y][first.x].size();
        diedMonsterCnt[first.y][first.x][curTurn] += originMonster[first.y][first.x].size();
        originMonster[first.y][first.x] = new ArrayList<>();

        diedMonster[second.y][second.x][curTurn] += originMonster[second.y][second.x].size();
        diedMonsterCnt[second.y][second.x][curTurn] += originMonster[second.y][second.x].size();
        originMonster[second.y][second.x] = new ArrayList<>();
    }


    static void step4_RemoveDiedMonster(int curTurn) {
        if (curTurn >= 2) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    diedMonster[i][j][curTurn] -= diedMonsterCnt[i][j][curTurn - 2];
                }
            }
        }
    }

    static void step5_RealCopy() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (Integer dir : copyMonster[i][j]) {
                    originMonster[i][j].add(dir);
                }
                copyMonster[i][j].clear();
            }
        }
    }

    static int getResult() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                count += originMonster[i][j].size();
            }
        }
        return count;
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
