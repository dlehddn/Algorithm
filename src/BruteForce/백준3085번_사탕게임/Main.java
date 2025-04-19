package BruteForce.백준3085번_사탕게임;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 시간복잡도 계산
 * 두칸 고르기 최악 -> 2500 * 4 = 10,000
 * 두칸 고르기 중복제외 -> 5,000
 * 교환하고 완전탐색 5,000
 * 25,000,000 or 50,000,000
 */
public class Main {
    static char[][] arr;
    static int N, max;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        max = 1;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = line.charAt(j);
            }
        }

        boolean[][] visited = new boolean[N * N][N * N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    int nY = i + dy[k];
                    int nX = j + dx[k];
                    if (outOfRange(nY, nX) || isSame(i, j, nY, nX) || visited[i * N + j][nY * N + nX]) continue;
                    findMax(i, j, nY, nX);
                    visited[i * N + j][nY * N + nX] = true;
                    visited[nY * N + nX][i * N + j] = true;
                }
            }
        }
        System.out.println(max);
    }

    static void swap(int y1, int x1, int y2, int x2) {
        char tmp = arr[y2][x2];
        arr[y2][x2] = arr[y1][x1];
        arr[y1][x1] = tmp;
    }

    static void findMax(int y1, int x1, int y2, int x2) {
        swap(y1, x1, y2, x2);
        for (int i = 0; i < N; i++) {
            int tmp = 1;
            char prev = ' ';
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == prev) {
                    tmp++;
                } else {
                    max = Math.max(max, tmp);
                    tmp = 1;
                    prev = arr[i][j];
                }
            }
            max = Math.max(max, tmp);
        }
        for (int i = 0; i < N; i++) {
            int tmp = 1;
            char prev = ' ';
            for (int j = 0; j < N; j++) {
                if (arr[j][i] == prev) {
                    tmp++;
                } else {
                    max = Math.max(max, tmp);
                    tmp = 1;
                    prev = arr[j][i];
                }
            }
            max = Math.max(max, tmp);
        }
        swap(y1, x1, y2, x2);
    }

    static boolean isSame(int y1, int x1, int y2, int x2) {
        if (arr[y1][x1] != arr[y2][x2]) return false;
        return true;
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }
}
