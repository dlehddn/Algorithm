package Samsung.왕실의기사대결;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 디버깅
 * 1. 점수 까는건 실제 움직인 애들만 + 명령 받은 애는 점수 까이지 않음
 * 2. 이미 죽은 기사는 명령을 받지 못함,,, (TC 19번)
 * -> 당시 시험장에서 이 조건 처리 안해줘서 틀린듯
 * -> 소요시간 3시간
 */

public class Main {
    static Knight[] knights;
    static Command[] commands;
    static int[][] knightMap, wallMap;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static int L, N, Q;
    static Set<Integer> moveSet;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/Samsung/왕실의기사대결/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        knights = new Knight[N + 1];
        knightMap = new int[L][L];
        wallMap = new int[L][L];
        commands = new Command[Q];
        moveSet = new HashSet<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                wallMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            knights[i] = new Knight(new Pair(y, x), new Pair(y + h - 1, x + w - 1), k, k);
            for (int j = y; j <= y + h - 1; j++) {
                for (int l = x; l <= x + w - 1; l++) {
                    knightMap[j][l] = i;
                }
            }
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int who = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            commands[i] = new Command(who, dir);
        }

        for (int i = 0; i < Q; i++) {
            if (knights[commands[i].who].remainLife == 0) continue; // 디버깅 포인트
            if (step1_canGo(commands[i].who, commands[i].dir)) {
                step2_moveAndCalculate(commands[i].who, commands[i].dir);
            }
        }
        System.out.println(makeResult());
    }

    static int makeResult() {
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (knights[i].remainLife > 0) {
                cnt += knights[i].totalLife - knights[i].remainLife;
            }
        }
        return cnt;
    }

    static void step2_moveAndCalculate(int knightNum, int dir) {
        setting(dir);
        knightMap = new int[L][L];
        for (int i = 1; i <= N; i++) {
            loop:
            for (int j = knights[i].startCorner.y; j <= knights[i].endCorner.y; j++) {
                for (int k = knights[i].startCorner.x; k <= knights[i].endCorner.x; k++) {
                    knightMap[j][k] = i;
                    // 디버깅 포인트
                    if (wallMap[j][k] == 1 && knightNum != i && moveSet.contains(i)) {
                        knights[i].remainLife--;
                    }
                    if (knights[i].remainLife == 0) {
                        for (int l = knights[i].startCorner.y; l <= knights[i].endCorner.y; l++) {
                            for (int m = knights[i].startCorner.x; m <= knights[i].endCorner.x; m++) {
                                knightMap[l][m] = 0;
                            }
                        }
                        break loop;
                    }
                }
            }
        }
    }

    static void setting(int dir) {
        for (Integer num : moveSet) {
            if (dir == 0) {
                knights[num].startCorner.y--;
                knights[num].endCorner.y--;
            } else if (dir == 1) {
                knights[num].startCorner.x++;
                knights[num].endCorner.x++;
            } else if (dir == 2) {
                knights[num].startCorner.y++;
                knights[num].endCorner.y++;
            } else if (dir == 3) {
                knights[num].startCorner.x--;
                knights[num].endCorner.x--;
            }
        }
    }

    static boolean step1_canGo(int knightNum, int dir) {
        moveSet = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.add(knightNum);
        moveSet.add(knightNum);

        while (!q.isEmpty()) {
            Integer poll = q.poll();
            if (visited[poll]) continue;
            visited[poll] = true;
            int[] range = findRange(poll, dir);
            for (int i = range[2]; i <= range[3]; i++) {
                for (int j = range[0]; j <= range[1]; j++) {
                    if (outOfRange(i, j)) return false;
                    if (wallMap[i][j] == 2) return false;
                    if (knightMap[i][j] != 0) {
                        q.add(knightMap[i][j]);
                        moveSet.add(knightMap[i][j]);
                    }
                }
            }
        }
        return true;
    }

    static int[] findRange(int knightNum, int dir) {
        Knight knight = knights[knightNum];
        Pair startCorner = knight.startCorner;
        Pair endCorner = knight.endCorner;
        int sX = 0, sY = 0, eX = 0, eY = 0;
        if (dir == 0) {
            sX = startCorner.x;
            eX = endCorner.x;
            sY = startCorner.y - 1;
            eY = startCorner.y - 1;
        } else if (dir == 1) {
            sX = endCorner.x + 1;
            eX = endCorner.x + 1;
            sY = startCorner.y;
            eY = endCorner.y;
        } else if (dir == 2) {
            sX = startCorner.x;
            eX = endCorner.x;
            sY = endCorner.y + 1;
            eY = endCorner.y + 1;
        } else if (dir == 3) {
            sX = startCorner.x - 1;
            eX = startCorner.x - 1;
            sY = startCorner.y;
            eY = endCorner.y;
        }
        return new int[]{sX, eX, sY, eY};
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= L || x >= L;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Knight {
        Pair startCorner, endCorner;
        int totalLife, remainLife;

        public Knight(Pair startCorner, Pair endCorner, int totalLife, int remainLife) {
            this.startCorner = startCorner;
            this.endCorner = endCorner;
            this.totalLife = totalLife;
            this.remainLife = remainLife;
        }
    }

    static class Command {
        int who, dir;

        public Command(int who, int dir) {
            this.who = who;
            this.dir = dir;
        }
    }
}
