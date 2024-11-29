package Floyd.백준1613번_역사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, K, S;
    static int[][] graph;
    static List<History> questions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start][end] = -1;
        }
        S = Integer.parseInt(br.readLine());
        questions = new ArrayList<>();
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            questions.add(new History(start, end));
        }

        for (int i = 1; i < N + 1; i++) {
            // 경유지
            for (int j = 1; j < N + 1; j++) {
                // 출발지
                for (int k = 1; k < N + 1; k++) {
                    // 도착지
                    if (graph[j][i] == -1 && graph[i][k] == -1) {
                        graph[j][k] = -1;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (History question : questions) {
            if (graph[question.start][question.end] == -1) {
                sb.append(-1).append("\n");
            } else if (graph[question.end][question.start] == -1) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static class History {
        int start, end;

        public History(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
