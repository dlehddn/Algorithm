package Samsung.마법사상어와토네이도;

import java.io.*;
import java.util.*;

public class Main {
    /**
     * 0. 몇 칸 전진하는지 파악하기
     * 1. 바라보는 방향으로 한칸 이동하기
     * 2. 해당 방향으로 움질일 때 주위에 모레를 흩뿌리기
     * 3. 밖으로 흩어지는 모레 잡기 (try-catch)
     */
    private static int[][] map;
    private static int N, sandSum, dir, moveCount, curY, curX;
    private static int[] dx = {-1, 0, 1, 0};  // 좌, 하, 우, 상 순서
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] spreadX = {{-1, -2, -1, -1, 0, 0, 0, 0, 1, 1}, {0, 0, -1, 1, 2, -2, 1, -1, 1, -1},
            {1, 2, 1, 1, 0, 0, 0, 0, -1, -1}, {0, 0, -1, 1, -2, 2, -1, 1, -1, 1}};
    private static int[][] spreadY = {{0, 0, 1, -1, -2, 2, -1, 1, -1, 1}, {1, 2, 1, 1, 0, 0, 0, 0, -1, -1},
            {0, 0, -1, 1, 2, -2, 1, -1, 1, -1}, {-1, -2, -1, -1, 0, 0, 0, 0, 1, 1}};
    private static int[] power = {0, 5, 10, 10, 2, 2, 7, 7, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        dir = 0;
        sandSum = 0;
        moveCount = 0;
        N = Integer.parseInt(br.readLine());
        curX = N / 2;
        curY = N / 2;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 3 / 2 + 1 =2
        boolean keepGoing = true;
        while (keepGoing) {
            int temp;
            if (moveCount == 0) {
                temp = 1;
            } else {
                temp = moveCount / 2 + 1;
            }
            while (temp > 0) {
                move();
                spreadSand();
                if (curY == 0 && curX == 0) {
                    keepGoing = false;
                    break;
                }
                temp--;
            }
            dir++;
            moveCount++;
            if (dir == 4) {
                dir = 0;
            }
            for(int[] a : map) {
            for(int b : a) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
        }


        System.out.println(sandSum);

    }

    private static void move() {
        curY += dy[dir];
        curX += dx[dir];
//        System.out.println(curY + "," + curX);
    }

    private static void spreadSand() { // 여기 걍 다시 만들면 될듯
        int sum = 0;
        for(int i = 1; i <= 9; i++) {
            int temp = map[curY][curX] * power[i] / 100;
            sum += temp;
            try {
                map[curY + spreadY[dir][i]][curX + spreadX[dir][i]] += temp;
            } catch (ArrayIndexOutOfBoundsException e) {
                sandSum += temp;
            }
        }
        map[curY][curX] -= sum;
        try {
            map[curY + spreadY[dir][0]][curX + spreadX[dir][0]] += map[curY][curX];
        } catch (ArrayIndexOutOfBoundsException e) {
            sandSum += map[curY][curX];
        }
        map[curY][curX] = 0;

    }
}
