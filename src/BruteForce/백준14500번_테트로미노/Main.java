package BruteForce.백준14500번_테트로미노;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, max;
    static int[][] map;
    static int[] dy = {1, -1, 0, 0}; // 하 상 우 좌
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        combDfs(0, new ArrayList<>());
        combBfs(0, 0, new ArrayList<>());
        System.out.println(max);
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= M;
    }

    static void checkBfs(List<Integer> directions) {
        for (int i = 0; i < N; i++) {
            loop:
            for (int j = 0; j < M; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    int curBlockY = i;
                    int curBlockX = j;
                    if (k != 0) {
                        curBlockY += dy[directions.get(k - 1)];
                        curBlockX += dx[directions.get(k - 1)];
                    }
                    if (outOfRange(curBlockY, curBlockX)) continue loop;
                    sum += map[curBlockY][curBlockX];
                }
                max = Math.max(max, sum);
            }
        }
    }

    static void checkDfs(List<Integer> directions) {
        for (int i = 0; i < N; i++) {
            loop:
            for (int j = 0; j < M; j++) {
                int curBlockY = i;
                int curBlockX = j;
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    if (k != 0) {
                        curBlockY += dy[directions.get(k - 1)];
                        curBlockX += dx[directions.get(k - 1)];
                    }
                    if (outOfRange(curBlockY, curBlockX)) continue loop;
                    sum += map[curBlockY][curBlockX];
                }
                max = Math.max(max, sum);
            }
        }
    }

    static void combBfs(int cnt, int start, List<Integer> directions) {
        if (cnt == 3) {
            checkBfs(directions);
            return;
        }

        for (int i = start; i < 4; i++) {
            directions.add(i);
            combBfs(cnt + 1, i + 1, directions);
            directions.remove(directions.size() - 1);
        }
    }

    static boolean isNotDuplicated(List<Integer> dir) {
        return dir.get(0) + dir.get(1) != 1 && dir.get(0) + dir.get(1) != 5
                && dir.get(1) + dir.get(2) != 1 && dir.get(1) + dir.get(2) != 5;
    }


    static void combDfs(int cnt, List<Integer> directions) {
        if (cnt == 3) {
            if (isNotDuplicated(directions)) {
                checkDfs(directions);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            directions.add(i);
            combDfs(cnt + 1, directions);
            directions.remove(directions.size() - 1);
        }
    }

}
