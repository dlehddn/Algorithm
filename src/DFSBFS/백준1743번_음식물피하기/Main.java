package DFSBFS.백준1743번_음식물피하기;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
        static int[] x_arr = {1, -1, 0, 0};
        static int[] y_arr = {0, 0, 1, -1};
        static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] coordinate = new int[y][x];
        boolean[][] visited = new boolean[y][x];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dy = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            coordinate[dy - 1][dx - 1] = 1;
        }

        int answer = 0;
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                if(coordinate[i][j] == 1) {
                    count = 0;
                    dfs(coordinate, visited, i, j);
                    answer = Math.max(answer, count);
                }
            }
        }
        bw.write(Integer.toString(answer));
        bw.flush();
        bw.close();
    }

    private static void dfs(int[][] coordinate, boolean[][] visited, int dy, int dx) {
        count++;
        visited[dy][dx] = true;

        for(int i = 0; i < 4; i++) {
            int next_x = dx + x_arr[i];
            int next_y = dy + y_arr[i];
            try {
                if (coordinate[next_y][next_x] == 1 && !visited[next_y][next_x]) {
                    dfs(coordinate, visited, next_y, next_x);
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
        }
    }
}
