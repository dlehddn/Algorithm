package Floyd.백준1613번_역사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Resolve {
    static int N, K, S;
    static int[][] connections;
    static final int PREV = -1;
    static final int AFTER = 1;
    static final int AMBIGUOUS= 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        connections = new int[N + 1][N + 1];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            connections[s][e] = PREV;
            connections[e][s] = AFTER;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    if (j == k) continue;
                    if (connections[j][i] == PREV && connections[i][k] == PREV) {
                        connections[j][k] = PREV;
                    }
                    if (connections[j][i] == AFTER && connections[i][k] == AFTER) {
                        connections[j][k] = AFTER;
                    }
                }
            }
        }

        S = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(connections[s][e]).append("\n");
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
