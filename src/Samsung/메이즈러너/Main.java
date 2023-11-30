package Samsung.메이즈러너;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.StringTokenizer;
import java.util.concurrent.SynchronousQueue;

/**
 * 이 문제에서는 정사각형 범위가 최대 10 * 10 이다
 * 해당 범위에 대해서 최대 10 * 10 * 10 * 10의 4중 for문을 사용해야해서 이게 될까? 고민했는데
 * 범위가 작아서 ㅇㅇㅇ 상관없음 이정도는 실제로 정답 시간 111ms임
 *
 * 중요! 디버깅 한곳은 딱 한곳이었는데, 처음에 동시에 이동해야한다는 조건때문에 이동을 tmp에 먼저 하고, 나중에 가져오는 식으로 했는데
 * 단순하게 tmp[][] = 원본[][] 처럼 해버리니까 다른놈이 오면 이전께 초기화가 된다.
 * tmp[][]도 누적합으로 계산해야하는거, 잊지말자!
 */

public class Main {

    static int[][] map;
    static int[][] human;
    static Pair exit;
    static Square square;
    static int N, M, K, outCnt, moveCnt;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        human = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            if (i != M) {
                human[y][x] += 1;
            } else {
                exit = new Pair(y, x);
            }
        }
        while (K > 0) {
            K--;
            step1_MoveAll();
            if (outCnt == M) {
                break;
            }
            step2_Spin();
        }
        System.out.println(moveCnt);
        int resultY = exit.y + 1;
        int resultX = exit.x + 1;
        System.out.println(resultY + " " + resultX);

        // 테스트 구간 //
//        step1_MoveAll();
//        step2_Spin();
//        step1_MoveAll();
//        step2_Spin();
//        System.out.println("exit = (" + exit.y + ", " + exit.x + ")");
//        System.out.println("square = (" + square.y + ", " + square.x + ", " + square.size + ")");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(human[i][j] + " ");
//            }
//            System.out.println();
//        }
        /////////////////


    }

    static void step1_MoveAll() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(human[i][j] == 0) continue;
                boolean stay = true;
                for (int k = 0; k < 4; k++) {
                    int nextY = i + dy[k];
                    int nextX = j + dx[k];
                    if (canGo(nextY, nextX, i, j)) {
                        moveCnt += human[i][j];
                        tmp[nextY][nextX] += human[i][j];
                        if (nextY == exit.y && nextX == exit.x) {
                            outCnt += tmp[nextY][nextX];
                            tmp[nextY][nextX] = 0;
                        }
                        stay = false;
                        break;
                    }
                }
                if (stay) {
                    tmp[i][j] += human[i][j];
                }
            }
        }
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(tmp[i][j] + " ");
//            }
//            System.out.println();
//        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                human[i][j] = tmp[i][j];
            }
        }
    }

    static boolean canGo(int nextY, int nextX, int curY, int curX) {
        if(nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) return false;
        if(map[nextY][nextX] != 0) return false;

        int curDistance = Math.abs(curX - exit.x) + Math.abs(curY - exit.y);
        int nextDistance = Math.abs(nextX - exit.x) + Math.abs(nextY - exit.y);
        if(nextDistance >= curDistance) return false;
        return true;
    }

    static void step2_Spin() {
        findSquare();
        spinning();
    }

    static void findSquare() {
        int minSize = Integer.MAX_VALUE;
        boolean containExit, containHuman;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (human[i][j] > 0) {
                    int tmpSize = Math.max(Math.abs(i - exit.y), Math.abs(j - exit.x)) + 1;
                    if (minSize > tmpSize) {
                        minSize = tmpSize;
                    }
                }
            }
        }
        // 최소 정사각형 사이즈 찾기 완료

        for (int i = 0; i <= N - minSize; i++) {
            for (int j = 0; j <= N - minSize; j++) {
                containExit = false;
                containHuman = false;
                for (int a = i; a < i + minSize; a++) {
                    for (int b = j; b < j + minSize; b++) {
                        if (exit.y == a && exit.x == b) {
                            containExit = true;
                        }
                        if (human[a][b] > 0) {
                            containHuman = true;
                        }
                        if (containExit && containHuman) {
                            square = new Square(i, j, minSize);
                            return;
                        }
                    }
                }
            }
        }
    }

    static void spinning() {
        int[][] mapTmp = new int[N][N];
        int[][] humanTmp = new int[N][N];
        Pair exitTmp = new Pair(-1, -1);
        Pair exitTmp2 = new Pair(exit.y - square.y, exit.x - square.x);

        int[][] mapTmp2 = new int[N][N];
        int[][] humanTmp2 = new int[N][N];
        Square squareTmp = new Square(0, 0, square.size);

        for (int i = 0; i < square.size; i++) {
            for (int j = 0; j < square.size; j++) {
                humanTmp2[i][j] = human[i + square.y][j + square.x];
                mapTmp2[i][j] = map[i + square.y][j + square.x];
            }
        }
        for (int i = 0; i < square.size; i++) {
            for (int j = 0; j < square.size; j++) {
                mapTmp[i][j] = mapTmp2[squareTmp.size - j - 1][i];
                if (mapTmp2[squareTmp.size - j - 1][i] > 0) {
                    mapTmp[i][j] -= 1;
                }
                if (exitTmp2.y == squareTmp.size - j - 1 && exitTmp2.x == i) {
                    exitTmp = new Pair(i, j);
                }
                if (humanTmp2[squareTmp.size - j - 1][i] > 0) {
                    humanTmp[i][j] = humanTmp2[squareTmp.size - j - 1][i];
                }
            }
        }
        for (int i = 0; i < square.size; i++) {
            for (int j = 0; j < square.size; j++) {
                human[i + square.y][j + square.x] = humanTmp[i][j];
                map[i + square.y][j + square.x] = mapTmp[i][j];
            }
        }
        exit = new Pair(exitTmp.y + square.y, exitTmp.x + square.x);

    }


    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    static class Square {
        int size;
        int y;
        int x;

        public Square(int y, int x, int size) {
            this.size = size;
            this.y = y;
            this.x = x;
        }
    }
}

