package DP.백준2096번_내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Second {
    static int[][] board, maxDp, minDp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][3];
        maxDp = new int[N][3];
        minDp = new int[N][3];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        maxDp[0][0] = board[0][0];
        maxDp[0][1] = board[0][1];
        maxDp[0][2] = board[0][2];
        minDp[0][0] = board[0][0];
        minDp[0][1] = board[0][1];
        minDp[0][2] = board[0][2];
        StringBuilder sb = new StringBuilder();
        // find Max Dp
        for (int i = 1; i < N; i++) {
            maxDp[i][0] = Math.max(maxDp[i - 1][0], maxDp[i - 1][1]) + board[i][0];
            maxDp[i][1] = Math.max(Math.max(maxDp[i - 1][0], maxDp[i - 1][1]), maxDp[i - 1][2]) + board[i][1];
            maxDp[i][2] = Math.max(maxDp[i - 1][1], maxDp[i - 1][2]) + board[i][2];
        }
        sb.append(Arrays.stream(maxDp[N - 1]).max().orElse(0)).append(" ");

        //find Min Dp
        for (int i = 1; i < N; i++) {
            minDp[i][0] = Math.min(minDp[i - 1][0], minDp[i - 1][1]) + board[i][0];
            minDp[i][1] = Math.min(Math.min(minDp[i - 1][0], minDp[i - 1][1]), minDp[i - 1][2]) + board[i][1];
            minDp[i][2] = Math.min(minDp[i - 1][1], minDp[i - 1][2]) + board[i][2];
        }
        sb.append(Arrays.stream(minDp[N - 1]).min().orElse(0));
        System.out.println(sb.toString());
    }
}
