package Simulation.백준3197번_백조의호수;

import java.io.*;
import java.util.*;

public class Try3 {

    private static int H, W, count;
    private static char[][] lake;
    private static int swan1_X, swan1_Y, swan2_X, swan2_Y;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static boolean isMeet;
    private static boolean[][] visited;
    private static Queue<int[]> waterQ = new LinkedList<>();
    private static Queue<int[]> newStart = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        initLakeAndSearchSwan(br);
        visited = new boolean[H][W];

        bfs(swan1_Y, swan1_X);
        while (true) {
            if (isMeet) {
                break;
            }
            nextDay();
            int size = newStart.size();
            while (size-- > 0) {
                int[] poll = newStart.poll();
                bfs(poll[0], poll[1]);
            }
        }

        bw.write(Integer.toString(count));
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
                if (line.charAt(j) == '.' || line.charAt(j) == 'L') {
                    waterQ.add(new int[]{i, j});
                }
            }
        }
    }

    private static void nextDay() {
        int size = waterQ.size();
		while (size-- > 0) {
			int[] currentWaterPoint = waterQ.poll();

			for (int i = 0; i < 4; i++) {
                int nextY = currentWaterPoint[0] + dy[i];
                int nextX = currentWaterPoint[1] + dx[i];
                try {
                    if (lake[nextY][nextX] == 'X') {
                        lake[nextY][nextX] = '.';
                        waterQ.add(new int[]{nextY, nextX});
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
			}
		}
        count++;
    }

    private static void bfs(int currentY, int currentX) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{currentY, currentX});
        visited[currentY][currentX] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = poll[1] + dx[i];
                int nextY = poll[0] + dy[i];
                try {
                    if (lake[nextY][nextX] != 'X' && !visited[nextY][nextX]) {
                        visited[nextY][nextX] = true;
                        q.add(new int[]{nextY, nextX});
                    } else if (lake[nextY][nextX] == 'X') {
                        newStart.add(new int[]{poll[0], poll[1]});
                    }
                    if (nextX == swan2_X && nextY == swan2_Y) {
                        isMeet = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
        }
    }
}

//Answer : 4
//100 100
//.........X...XXXXXXXXXXXXX.XXXXXXXXXXXXX.....XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.X.X
//..X..........XXXXXXXXXXXXX.XXXXXXXXXXXXX.....XXXXXXXXXXXXXXLXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.X.X.
//.............XXXXXXXXXXXXX.XXXXXXXXXXXXX....XXXXXXXXXXXXXXXXXXXXXXXX.X.XXX.X.X.X.X.X.X.X.X.X.X.X.X.X
//.............XXXXXXXXXXXXX.XXXXXXXXXXXXX.....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.
//.............XXXXXXXXXXXXX.XXXXXXXXXXXXX....XXXXXXXXXXXXXXX.XXXXXXXXXXXX.XXX.X.X.X.X.X.X.X.X.X.X.X.X
//.............XXXXXXXXXXXXXXXXXXXXXXXXXXX....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.
//.............XXXXXXXXXXXXXXXXXXXXXXXXXXX......XXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.X.X
//.............XXXXXXXXXXXXXXXXXXXXXXXXXXX......XXXXXXXXXXXXXXX.XXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.X.X.
//.............XXXXXXXXXXXXX.XXXXXXXXXXXXX.........XXXXXXXXXXX.XXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.X.X.X
//.............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.X.
//.............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.X
//.............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X.
//..............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.X
//X.X............XXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X.
//.X..............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.X
//X.X.X...........XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X.
//.X.X.............XXXXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.X
//X.X.X.............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.
//.X.X...............XXXXXXXXXXXXXXX.X.XXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X
//X.X.X...X...........XXXXXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.
//.X.X.................XXXXXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X
//X.X...X...............XXXXXXXXXXXXXXXX..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.
//.......X...X...........XXXXXXXXXXXXXXXXXXX.XX.XXXXX.XXXXX.XXXXX.XXXX.XXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//......................X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.X.......X...............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X.........................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//.X.........................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X...........................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.X...........................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//........X......................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//................................XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXX.X.X.X.X.X.X
//............................X....XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//.X...X.....................X.....X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXX.X.X.X.X.X.X
//......X...............X.............XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.....X.............................X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//..................................X.X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.X...X.X.........................X.X.....X...X.XXXXXXXXXXX.X.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X.X...X.........................X.X.X...........XXXXXXXXXXX.XXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//.....X.X.........................X.X.X.........X.XXXXX.XXX.X.XXXXXXXXXXXXX.XXXXXXXXXXXXX.X.X.X.X.X.X
//..X.X.X...........................X.X.X.X.....X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//...X.X.............................X.X.X.X.X...X.X.XXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//......X...........................X.X.X.X.X.X...X..XX.XXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//.X....XXXXXXXXXXX..................X...X.X.X.X......XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X....XXXXXXXXXXXXX........................X.........XXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.X..XXXXXXXXXXXXXXX................................XXXXXXX.X.XXXXXXXXXXXXX.XXXXXXXXXXXXX.X.X.X.X.X.X
//X.XXXXXXXXXXXXXXXXXX................................X.X.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.XXXXXXXXXXXXXXXXXXXX..............................X.X.X.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//.XXXXXXXXXXXXXXXXXXXXX..............................X.X.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//XXXXXXXXXXXXXXXXXXXXXXX........................X.....X.X.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//XXXXXXXXXXXXXXXXXXXXXXXX..........X.....X...........X.X.X.X.XXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//XXXXXXXXXXXXXXXXXXXXXXXXX......................X.....X.X.X.X.XXXXXXXXXXXXX.XXXXXXXXXXXXX.X.X.X.X.X.X
//XXXXXXXXXXXXXXXXXXXXXXXXXX..X...................X.....X.X.X.XXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//XXXXXXXXXXXXXXXXXXXXXXXXXXX............................X.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXX...........................X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.................X.........X...XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//XXXXXX.X.XXXXLX..XXXXXXXXXXXXXX.............X...X.......X....XXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.................X.............XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.............X.X.X.X..........XXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//XXXXXXXXXXXXXXXX.XXXXXXXXXXXXX...........X...X...X...........XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX......................X........XXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...X.X.....X...................XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX....X..........................XXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX...X.X...........X.X.........X.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX....X...............X.......X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//..XXXXXXXXXXXXXX.XXXXXXXXXXXXX.....X...........X.............XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//...XXXXXXXXXXXXX.XXXXXXXXXXXXX..................X.....X.X....XXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXXXXXXXXXXXXXXXX.................X.............XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//...XXXXXXXXXXXXXXXXXXXXXXXXXXX....X...........X.X...........XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXXXXXXXXXXXXXXXX...................X...........XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X..XXXXXXXXXXXXXXXXXXXXXXXXXXX...............................XXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXX.XXXXXXXXXXXXX.....................X.X.......XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//...XXXXXXXXXXXXX.XXXXXXXXXXXXX......................X...X....XXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXX.XXXXXXXXXXXXX.........X.................X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//...XXXXXXXXXXXXXXXXXXXXXXXXXXX......................X...X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.X.XXXXXXXXXXXXXXXXXXXXXXXXXXX...................X.......X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//...XXXXXXXXXXXXXXXXXXXXXXXXXXX............................X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXXXXXXXXXXXXXXXX...........X.................X.XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X..XXXXXXXXXXXXXXXXXXXXXXXXXXX..........X.X...........X...X.XXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXXXXXXXXXXXXXXXX.........X.......X.........X...XXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X..XXXXXXXXXXXXX.XXXXXXXXXXXXX..............X...........X....XXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.X.XXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//.X.XXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXX.X.X.X.X.X.X
//...XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X..XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//...XXXXXXXXXXXXX.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.
//.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X.XXXXXXXXXXXXXXX.X.XX.XXXXX.XXXX...XXXX.X.XXXXXX...XX.X.XXX.XXX.....XXXXX.XXXXXXXXXXXXXX.X.X.X.X.X.
//.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X
//X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.
//.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X
//X.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.
//.X.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X
//X.X.X.X.XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.X.X.X.X.X.X.X.X.
