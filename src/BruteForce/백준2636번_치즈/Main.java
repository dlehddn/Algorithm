package BruteForce.백준2636번_치즈;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][][] map;
    static int N, M, cnt;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[51][N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[0][i][j] = Integer.parseInt(st.nextToken());
                if (map[0][i][j] == 1) cnt++;
            }
        }

        for (int i = 1; i < 51; i++) {
            int remain = cnt;
            boolean[][] visited = new boolean[N][M];

            remain = bfs(i, remain, visited);
            createNextMap(i, visited);

            if (remain == 0) {
                sb.append(i).append("\n");
                sb.append(cnt);
                break;
            }
            cnt = remain;
        }
        System.out.println(sb);
    }

    private static void createNextMap(int i, boolean[][] visited) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < M; k++) {
                if (!visited[j][k] && map[i - 1][j][k] == 1) {
                    map[i][j][k] = 1;
                }
            }
        }
    }

    private static int bfs(int i, int remain, boolean[][] visited) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();

            for (int j = 0; j < 4; j++) {
                int nY = poll.y + dy[j];
                int nX = poll.x + dx[j];
                if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if (visited[nY][nX]) continue;
                visited[nY][nX] = true;
                if (map[i - 1][nY][nX] == 0) {
                    q.add(new Node(nY, nX));
                } else {
                    remain--;
                }
            }
        }
        return remain;
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
