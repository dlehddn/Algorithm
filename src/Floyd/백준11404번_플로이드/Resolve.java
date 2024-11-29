package Floyd.백준11404번_플로이드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Resolve {

    static int N, M;
    static int[][] dist;
    static final int INF = 100_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        dist = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                dist[i][j] = INF;
            }
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            dist[start][end] = Math.min(dist[start][end], cost);
        }

        for (int i = 0; i < N + 1; i++) {
            // 경유지
            for (int j = 0; j < N + 1; j++) {
                // start
                for (int k = 0; k < N + 1; k++) {
                    // end
                    if (j == k) continue;
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                System.out.print(dist[i][j] == INF ? 0 + " " : dist[i][j] + " ");
            }
            System.out.println();
        }

    }
}
