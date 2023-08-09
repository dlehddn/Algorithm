package Simulation.백준3197번_백조의호수;

import java.io.*;
import java.util.*;

public class Try2 {

    private static int H, W, count;
    private static char[][] lake;
    private static int swan1_X, swan1_Y, swan2_X, swan2_Y;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static boolean isMeet;
    private static boolean[][] visited_swan1;
    private static boolean[][] visited_swan2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());


        initLakeAndSearchSwan(br);

        while (true) {
            if (isMeet) {
                break;
            }
            visited_swan1 = new boolean[H][W];
            visited_swan2 = new boolean[H][W];
            bfs();
            count++;
        }

        bw.write(Integer.toString(count-1));
        bw.flush();
        bw.close();
    }

    private static void initLakeAndSearchSwan(BufferedReader br) throws IOException {
        lake = new char[H][W];
        boolean isFirst = true;
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                lake[i][j] = line.charAt(j);
                if (line.charAt(j) == 'L') {
                    if (isFirst) {
                        swan1_X = j;
                        swan1_Y = i;
                        isFirst = false;
                    } else {
                        swan2_X = j;
                        swan2_Y = i;
                    }

                }
            }
        }
    }

    private static void bfs() {
        Queue<int[]> q_swan1 = new LinkedList<>();
        Queue<int[]> q_swan2 = new LinkedList<>();
        q_swan1.add(new int[]{swan1_Y, swan1_X});
        q_swan2.add(new int[]{swan2_Y, swan2_X});
        visited_swan1[swan1_Y][swan1_X] = true;
        visited_swan2[swan2_Y][swan2_X] = true;

        while (!q_swan1.isEmpty()) {
            int[] poll = q_swan1.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = poll[1] + dx[i];
                int nextY = poll[0] + dy[i];
                try {
                    if (lake[nextY][nextX] != 'X' && !visited_swan1[nextY][nextX]) {
                        visited_swan1[nextY][nextX] = true;
                        q_swan1.add(new int[]{nextY, nextX});
                    }
                    else if(lake[nextY][nextX] == 'X' && !visited_swan1[nextY][nextX]) {
                        lake[nextY][nextX] = '.';
                        visited_swan1[nextY][nextX] = true;
                    }
                    if (nextX == swan2_X && nextY == swan2_Y) {
                        isMeet = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
        }

        while (!q_swan2.isEmpty()) {
            int[] poll = q_swan2.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = poll[1] + dx[i];
                int nextY = poll[0] + dy[i];
                try {
                    if (lake[nextY][nextX] != 'X' && !visited_swan2[nextY][nextX]) {
                        visited_swan2[nextY][nextX] = true;
                        q_swan2.add(new int[]{nextY, nextX});
                    }
                    else if(lake[nextY][nextX] == 'X' && !visited_swan2[nextY][nextX]) {
                        lake[nextY][nextX] = '.';
                        visited_swan2[nextY][nextX] = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
        }
    }
}
