package Simulation.백준16234번_인구이동;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, L, R, day;
    static int[][] map;
    static Set<Integer> tmp, entire;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/Simulation/백준16234번_인구이동/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        entire = new HashSet<>();
        tmp = new HashSet<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean end = true;
        while (true) {
            entire.clear();
            end = true;
            for (int i = 0; i < N * N; i++) {
                if (entire.contains(i)) continue;
                if (findUnion(i)) {
                    end = false;
                    int share = tmp.stream()
                            .mapToInt(n -> map[n / N][n % N])
                            .sum() / tmp.size();
                    for (Integer country : tmp) {
                        map[country / N][country % N] = share;
                    }
                }
            }
            if (end) break;
            day++;
        }
        System.out.println(day);
    }

    static boolean findUnion(int country) {
        tmp.clear();
        tmp.add(country);
        entire.add(country);
        dfs(country / N, country % N);
        if (tmp.size() > 1) {
            return true;
        } else {
            return false;
        }
    }

    static void dfs(int y, int x) {
        for (int i = 0; i < 4; i++) {
            int nY = y + dy[i];
            int nX = x + dx[i];
            if (nY < 0 || nX < 0 || nY >= N || nX >= N) continue;
            int gap = Math.abs(map[nY][nX] - map[y][x]);
            if (gap >= L && gap <= R && !entire.contains(N * nY + nX) && !tmp.contains(N * nY + nX)) {
                tmp.add(N * nY + nX);
                entire.add(N * nY + nX);
                dfs(nY, nX);
            }
        }
    }
}
