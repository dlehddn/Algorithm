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

        for(int i = n/3; i < 2 * n/3; i++) {
            for(int j = n/3; j < 2 * n/3; j++) {
                paint[i+x][j+y] = ' ';
            }
        }
        star(n/3, paint, x,y);
        star(n/3, paint, x + n/3,y);
        star(n/3, paint,x + 2*n/3,y);
        star(n/3, paint,x,y + n/3);
        star(n/3, paint,x + 2*n/3, y + n/3);
        star(n/3, paint,x, y + 2*n/3);
        star(n/3, paint,x + n/3, y + 2*n/3);
        star(n/3, paint,x + 2*n/3, y + 2*n/3);
    }

}