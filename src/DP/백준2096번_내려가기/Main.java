package DP.백준2096번_내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map, maxs, mins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][3];
        maxs = new int[N][3];
        mins = new int[N][3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 3; i++) {
            maxs[0][i] = map[0][i];
            mins[0][i] = map[0][i];
        }

        for (int i = 1; i < N; i++) {
            maxs[i][0] = Math.max(maxs[i - 1][0], maxs[i - 1][1]) + map[i][0];
            maxs[i][1] = Math.max(Math.max(maxs[i - 1][0], maxs[i - 1][1]), maxs[i - 1][2]) + map[i][1];
            maxs[i][2] = Math.max(maxs[i - 1][1], maxs[i - 1][2]) + map[i][2];

            mins[i][0] = Math.min(mins[i - 1][0], mins[i - 1][1]) + map[i][0];
            mins[i][1] = Math.min(Math.min(mins[i - 1][0], mins[i - 1][1]), mins[i - 1][2]) + map[i][1];
            mins[i][2] = Math.min(mins[i - 1][1], mins[i - 1][2]) + map[i][2];
        }

        int max = Arrays.stream(maxs[N - 1])
                .max()
                .orElse(0);

        int min = Arrays.stream(mins[N - 1])
                .min()
                .orElse(0);

        System.out.println(max + " " + min);
    }
}
