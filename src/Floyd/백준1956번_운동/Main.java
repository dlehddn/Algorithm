package Floyd.백준1956번_운동;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V, E;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        dist = new int[V + 1][V + 1];
        for (int i = 0; i < V + 1; i++) {
            Arrays.fill(dist[i], 10000000);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            dist[a][b] = Math.min(dist[a][b], c);
        }

        for (int i = 1; i < V + 1; i++) {
            for (int j = 1; j < V + 1; j++) {
                for (int k = 1; k < V + 1; k++) {
                    dist[j][k] = Math.min(dist[j][k], dist[j][i] + dist[i][k]);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < V + 1; i++) {
            if (dist[i][i] < min) {
                min = dist[i][i];
            }
        }

        System.out.println(min != 10000000 ? min : -1);
    }
}
