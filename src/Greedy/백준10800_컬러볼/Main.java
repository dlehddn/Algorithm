package Greedy.백준10800_컬러볼;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, colorTypes;
    static List<Ball> balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        balls = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            colorTypes = Math.max(colorTypes, c);
            balls.add(new Ball(i, c, s));
        }
        balls.sort(Comparator.comparingInt(b -> b.size));
        int[] totalSum = new int[N + 1];
        int[][] colorSum = new int[colorTypes + 1][N + 1];
        StringBuilder sb = new StringBuilder();

        int prev = 0;
        int[] answer = new int[N];
        for (int i = 0; i < balls.size(); i++) {
            Ball curBall = balls.get(i);
            colorSum[curBall.color][i] += curBall.size;
            totalSum[i] += curBall.size;
            answer[curBall.idx] = totalSum[prev] - colorSum[curBall.color][prev];
            if (i + 1 < balls.size() && balls.get(i + 1).size > curBall.size) {
                prev = i;
            }
            if (i + 1 < balls.size()) {
                totalSum[i + 1] += totalSum[i];
                colorSum[curBall.color][i + 1] += colorSum[curBall.color][i];
            }
        }
        for (int i : answer) {
            sb.append(i).append("\n");
        }
        System.out.println(sb.toString());
    }

    static class Ball {
        int idx, color, size;

        public Ball(int idx, int color, int size) {
            this.idx = idx;
            this.color = color;
            this.size = size;
        }
    }

}
