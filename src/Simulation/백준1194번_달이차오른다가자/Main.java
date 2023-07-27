package Simulation.백준1194번_달이차오른다가자;


import java.io.*;
import java.util.*;

public class Main {

    private static int H, W, startX, startY, endX, endY;
    private static HashSet<Character> keySet = new HashSet<>();
    private static char[][] coordinate;
    private static boolean[][][] visited;
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static int[][] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        coordinate = new char[H][W];
        count = new int[H][W];
        visited = new boolean[H][W][64];

        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                coordinate[i][j] = line.charAt(j);
                if (line.charAt(j) == '0') {
                    startX = j;
                    startY = i;
                }
            }
        }
        int result = bfs(startY, startX);
        bw.write(Integer.toString(result));
        bw.flush();
        bw.close();

    }

    private static int bfs(int startY, int startX) {
        int currentKey = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startY, startX, currentKey});
        visited[startY][startX][currentKey] = true;

        while (!q.isEmpty()) {
            int[] poll = q.poll();
            currentKey = poll[2];
            for (int i = 0; i < 4; i++) {
                int nextX = poll[1] + dx[i];
                int nextY = poll[0] + dy[i];
                try {
                    char nextChar = coordinate[nextY][nextX];
                    if(nextChar != '#' && !visited[nextY][nextX][currentKey]) {
                        if (Character.isUpperCase(nextChar)) {
                            int door = 1 << nextChar - 'A';
                            if((currentKey & door) > 0) {
                                visited[nextY][nextX][currentKey] = true;
                                q.add(new int[]{nextY, nextX, currentKey});
                                count[nextY][nextX] = count[poll[0]][poll[1]] + 1;
                            }
                        }
                        else if (Character.isLowerCase(nextChar)) {
                            int newKey = 1 << nextChar - 'a';
                            int sumKey = currentKey | newKey;
                            if(!visited[nextY][nextX][sumKey]) {
                                visited[nextY][nextX][sumKey] = true;
                                q.add(new int[]{nextY, nextX, sumKey});
                                count[nextY][nextX] = count[poll[0]][poll[1]] + 1;
                            }
                        }
                        else {
                            visited[nextY][nextX][currentKey] = true;
                            q.add(new int[]{nextY, nextX, currentKey});
                            count[nextY][nextX] = count[poll[0]][poll[1]] + 1;
                        }
                        if (coordinate[nextY][nextX] == '1') {
                            return count[nextY][nextX];
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
        }
        return -1;

    }
}
