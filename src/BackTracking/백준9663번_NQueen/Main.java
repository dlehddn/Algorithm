package BackTracking.백준9663번_NQueen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 풀이 실패, 다시 풀자.
public class Main {

    static int N, result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        backTracking(0, new Pair(0, 0), new boolean[N][N]);
        System.out.println(result);

    }

    static void backTracking(int cnt, Pair start, boolean[][] visited) {
        if (cnt == N) {
            result++;
            return;
        }

        for (int i = start.y; i < N; i++) {
            for (int j = start.x; j < N; j++) {
                if (check(visited, new Pair(i, j))) {
                    visited[i][j] = true;
                    backTracking(cnt + 1, new Pair(start.y + 1, start.x) ,visited);
                    visited[i][j] = false;
                }
            }
        }
    }

    static boolean check(boolean[][] visited, Pair position) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) {
                    if (i == position.y || j == position.x) {
                        return false;
                    }
                    if (Math.abs(i - position.y) == Math.abs(j - position.x)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
