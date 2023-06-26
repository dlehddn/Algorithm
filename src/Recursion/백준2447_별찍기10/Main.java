package Recursion.백준2447_별찍기10;

import java.io.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        char[][] paint = new char[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                paint[i][j] = '*';
            }
        }

        star(n, paint, 0, 0);
        for(char[] a : paint) {
            bw.write(a);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    private static void star(int n, char[][] paint, int x, int y) {

        if(n < 3) return;

        int nextSize = n/3;
        for(int i = nextSize; i < 2*nextSize; i++) {
            for(int j = nextSize; j < 2*nextSize; j++) {
                paint[i+x][j+y] = ' ';
            }
        }
        star(nextSize, paint, x,y);
        star(nextSize, paint, x + nextSize,y);
        star(nextSize, paint,x + 2*nextSize,y);
        star(nextSize, paint,x,y + nextSize);
        star(nextSize, paint,x + 2*nextSize, y + nextSize);
        star(nextSize, paint,x, y + 2*nextSize);
        star(nextSize, paint,x + n/3, y + 2*nextSize);
        star(nextSize, paint,x + 2*nextSize, y + 2*nextSize);
    }

}