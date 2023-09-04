package Simulation.백준14503번_로봇청소기;

import java.util.*;
import java.io.*;
public class Main {

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {-1, 0, 1, 0};
    // 인덱스 순서대로 북, 동, 남, 서를 의미
    private static int[][] room;
    private static boolean[][] visited;
    private static int N, M, dir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int initY = Integer.parseInt(st.nextToken());
        int initX = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        room = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // ---------------------------------------- 입력부 ----------------------------------------------

        recursion(initY, initX);
        System.out.println(count());

    }

    private static int changeDir(int dir) {
        if (dir == 0) {
            return 3;
        } else {
            return dir - 1;
        }
    }

    private static boolean isCleaned(int curY, int curX) {
        for (int i = 0; i < 4; i++) {
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            try {
                if (room[nextY][nextX] == 0 && !visited[nextY][nextX]) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
        }
        return false;
    }

    private static void recursion(int curY, int curX) {
        visited[curY][curX] = true;
        if(!isCleaned(curY, curX)) {
            int nextX = curX + dx[(dir + 2) % 4];
            int nextY = curY + dy[(dir + 2) % 4];
            // 0  1 2  3
            // 북 동 남 서
            try {
                if (room[nextY][nextX] != 1) {
//                    System.out.println(nextY + ", " + nextX);
                    recursion(nextY, nextX);
                }
                else {
                    return;
                }
            } catch(ArrayIndexOutOfBoundsException e) {}

        } else {
            dir = changeDir(dir);
//            System.out.println("현재 방향은" + dir);
            int nextX = curX + dx[dir];
            int nextY = curY + dy[dir];
            try {
                if (room[nextY][nextX] == 0 && !visited[nextY][nextX]) {
//                    System.out.println(nextY + ", " + nextX);
                    recursion(nextY, nextX);
                } else {
                    recursion(curY, curX);
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
        }
    }

    private static int count() {
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(visited[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }


}
