package Simulation.백준11559번_PuyoPuyo;

import java.io.*;
import java.util.*;

public class Main {

    private static int[] x_arr = {1, -1, 0, 0};
    private static int[] y_arr = {0, 0, 1, -1};
    private static char[][] coordinate;
    private static boolean[][] visited;
    private static ArrayList<int[]> tmpList = new ArrayList<>();
    private static ArrayList<int[]> totalList = new ArrayList<>();
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        coordinate = new char[12][6];
        visited = new boolean[12][6];

        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                coordinate[i][j] = line.charAt(j);
            }
        }

        while(allSearch()) {
            bomb();
            moveToDown();
            count++;
        }
//        for(char[] a : coordinate) System.out.println(Arrays.toString(a));
        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }



    private static void dfs(int dy, int dx) {
        visited[dy][dx] = true;
        tmpList.add(new int[]{dy, dx});

        for(int i = 0; i < 4; i++) {
            int next_x = dx + x_arr[i];
            int next_y = dy + y_arr[i];
            try {
                if (coordinate[next_y][next_x] == coordinate[dy][dx] && !visited[next_y][next_x]) {
                    dfs(next_y, next_x);
                }
            } catch (ArrayIndexOutOfBoundsException e) {}
        }
    }

    private static boolean allSearch() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (coordinate[i][j] != '.') {
                    dfs(i, j);
                    if (tmpList.size() >= 4) {
                        totalList.addAll(tmpList);
                    }
                    tmpList.clear();
                }
            }
        }
        visited = new boolean[12][6];
        if (!totalList.isEmpty()) return true;
        else return false;
    }

    private static void bomb() {
        for (int[] a : totalList) {
            coordinate[a[0]][a[1]] = '.';
        }
        totalList = new ArrayList<>();
    }

    private static void moveToDown() {
        for(int i = 0; i < 6; i++) {
            for(int j = 10; j >= 0; j--) {
                if(coordinate[j][i] != '.') {
                    char temp = coordinate[j][i];
                    coordinate[j][i] = '.';
                    int currentY = j;
                    while(currentY+1 <= 11 && coordinate[currentY+1][i] == '.') {
                        currentY++;
                    }
                    coordinate[currentY][i] = temp;
                }
            }
        }
    }
}


//......
//......
//......
//......
//......
//......
//......
//......
//.Y....
//.YG...
//RRYGG.
//RRYGG.