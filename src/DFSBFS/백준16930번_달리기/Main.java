package DFSBFS.백준16930번_달리기;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int cntSecond = 0;
    static int cntStraight = 0;
    static int startX, startY, endX, endY;
    static int K,N,M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(System.out)));
        StringTokenizer st = new StringTokenizer(br.readLine());


        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] coordinate = new int[N][M];
        int[][] count = new int[N][M];
        boolean[][] visited = new boolean[N][M];


        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                if (line.charAt(j) == '.') coordinate[i][j] = 1;
            }
        }

        String line = br.readLine();
        st = new StringTokenizer(line);

        startY = Integer.parseInt(st.nextToken()) - 1;
        startX = Integer.parseInt(st.nextToken()) - 1;
        endY = Integer.parseInt(st.nextToken()) - 1;
        endX = Integer.parseInt(st.nextToken()) - 1;


        bfs(coordinate, visited, count);
//        for(int[] a : count) System.out.println(Arrays.toString(a));
        if(count[endY][endX] == 0) bw.write("-1");
        else bw.write(Integer.toString(count[endY][endX]));
        bw.flush();
        bw.close();


    }

    private static void bfs(int[][] coordinate, boolean[][] visited, int[][] count) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startY, startX});
        visited[startY][startX] = true;
        while (!q.isEmpty()) {
            int[] xy = q.poll();

            for (int i = 0; i < 4; i++) {
                for(int j = 1; j <= K; j++) {
                    int next_x = xy[1] + dx[i] * j;
                    int next_y = xy[0] + dy[i] * j;

                    try {

                        if(coordinate[next_y][next_x] == 0) break;
                        if(coordinate[next_y][next_x] == 1 && !visited[next_y][next_x]) {
                            count[next_y][next_x] = count[xy[0]][xy[1]] + 1;
                            visited[next_y][next_x] = true;
                            if (xy[1] == endX && xy[0]== endY) return;
                            q.add(new int[]{next_y, next_x});
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                }
            }
        }
    }
}












//


//3 3 2
//..#
//..#
//#..
//1 1 3 3


