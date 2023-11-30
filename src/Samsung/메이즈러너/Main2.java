package Samsung.메이즈러너;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main2 {
    /**
     * member에서 각 자리마다 플레이어가 고정인데, 출구로 나가면 x, y 모두 -10으로 바꿔줄거다.
     */
    private static Pair[] member;
    private static Pair exit;
    private static int[][] map;
    private static int N, M, K;
    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};
    private static int squareX, squareY, squareSize;
    private static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        member = new Pair[M];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            if (i != M) {
                member[i] = new Pair(y, x);
            } else {
                exit = new Pair(y, x);
            }
        }

//        while (K-- > 0) {
        step1_MoveMember();
//            if (allOut()) {
//                System.out.println(sum);
//                System.out.println(exit.y + " " + exit.x);
//                break;
//            }
        step2_findSquare();
        step3_SpinSquare();

        step1_MoveMember();
        step2_findSquare();
        step3_SpinSquare();

//        step1_MoveMember();
//        step2_findSquare();
//        step3_SpinSquare();

//        }

        if (!allOut()) {
            System.out.println(sum);
            System.out.println(exit.y + " " + exit.x);
        }


        for (Pair a : member) {
            System.out.println("y = " + a.y + " , x = " + a.x);
        }


        for (int[] a : map) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("exitX = " + exit.x + "exitY = " + exit.y);
        System.out.println("spinx = " + squareX);
        System.out.println("spiny = " + squareY);
        System.out.println("spinSize = " + squareSize);
//

    }

    private static void step1_MoveMember() {
        for (int i = 0; i < M; i++) {
            Pair curMember = member[i];
            int nextY, nextX, curDistance, nextDistance;
            for (int j = 0; j < 4; j++) {
                nextY = curMember.y + dy[j];
                nextX = curMember.x + dx[j];
                curDistance = Math.abs(curMember.y - exit.y) + Math.abs(curMember.x - exit.x);
                nextDistance = Math.abs(nextY - exit.y) + Math.abs(nextX - exit.x);
                if (nextY < 0 || nextX < 0 || nextY >= N || nextY >= N) {
                    continue;
                }
                if (nextDistance >= curDistance || map[nextY][nextX] != 0) {
                    continue;
                }
                curMember.y = nextY;
                curMember.x = nextX;
                sum++;
                if (nextDistance == 0) {
                    curMember.y = -5;
                    curMember.x = -5;
                }
                break;
            }

        }
    }

    private static void step2_findSquare() {
        int minSize = Integer.MAX_VALUE;
        ArrayList<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            Pair curMember = member[i];
            int distanceY = Math.abs(curMember.y - exit.y);
            int distanceX = Math.abs(curMember.x - exit.x);
            if (distanceY > distanceX) {
                if (distanceY <= minSize) {
                    if (distanceY < minSize) {
                        tmp.clear();
                        minSize = distanceY + 1;
                    }
                    tmp.add(i);
                }
            } else {
                if (distanceX <= minSize) {
                    if (distanceX < minSize) {
                        tmp.clear();
                        minSize = distanceX + 1;
                    }
                    tmp.add(i);
                }
            }
        } // 여기까지하면 tmp에 이제 가장 사이즈 작은 녀석들이 다 들어감

        for (int i = 0; i + minSize - 1 < N; i++) {
            for (int j = 0; j + minSize - 1 < N; j++) {
                if (exit.x >= j && exit.x <= j + minSize - 1 && exit.y >= i && exit.y <= i + minSize - 1) {
                    for (int k = 0; k < member.length; k++) {
                        if (member[k].x >= j && member[k].x <= j + minSize - 1 && member[k].y >= i && member[k].y <= i + minSize - 1) {
                            squareX = j;
                            squareY = i;
                            squareSize = minSize;
                            return;
                        }
                    }
                }
            }
        }

    }

    private static void step3_SpinSquare() {
        int[][] tmpMap = new int[N][N];
        HashMap<Integer, int[]> tmpMember = new HashMap<>();
        Pair tmpExit = null;
        for (int i = squareY; i < squareY + squareSize; i++) {
            for (int j = squareX; j < squareX + squareSize; j++) {
                if (map[squareSize - 1 - j + squareX + squareY][i + Math.abs(squareX - squareY)] > 0) {
                    tmpMap[i][j] = map[squareSize - 1 - j + squareX + squareY][i + Math.abs(squareX - squareY)] - 1;
                } else {
                    tmpMap[i][j] = map[squareSize - 1 - j + squareX + squareY][i + Math.abs(squareX - squareY)];

                }

                if (exit.y == squareSize - 1 - j + squareX + squareY && exit.x == i + Math.abs(squareX - squareY)) {
                    tmpExit = new Pair(i, j);
                    System.out.println("회전한 출구의 위치는 " + i + "," + j);
                }

                for (int k = 0; k < M; k++) {
                    if (member[k].y == squareSize - 1 - j + squareX + squareY && member[k].x == i + Math.abs(squareX - squareY)) {
                        tmpMember.put(k, new int[]{i, j});
//                        System.out.println("회전한 사람의 위치는 " + i + "," + j);
                    }
                }
            }
        }

        for (int i = squareY; i < squareY + squareSize; i++) {
            for (int j = squareX; j < squareX + squareSize; j++) {
                map[i][j] = tmpMap[i][j];
            }
        }

        tmpMember.forEach((key, value) -> {
            member[key].y = value[0];
            member[key].x = value[1];
        });

        if (tmpExit != null) {
            exit.x = tmpExit.x;
            exit.y = tmpExit.y;
        }

    }


    private static boolean allOut() {
        boolean allOut = true;
        for (int i = 0; i < M; i++) {
            if (member[i].x != -5 || member[i].y != -5) {
                return false;
            }
        }
        return allOut;
    }


    static class Pair {
        int x, y;

        public Pair(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

}