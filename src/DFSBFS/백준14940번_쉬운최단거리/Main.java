package DFSBFS.백준14940번_쉬운최단거리;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] map, dist;
    static int N, M;
    static Node end;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    end = new Node(i, j, 0);
                }
            }
        }

        dist = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    dist[i][j] = 0;
                }
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(end);
        visited[end.y][end.x] = true;
        dist[end.y][end.x] = 0;

        while (!q.isEmpty()) {
            Node poll = q.poll();

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if(nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if (map[nY][nX] == 1 && !visited[nY][nX]) {
                    q.add(new Node(nY, nX, poll.cnt + 1));
                    dist[nY][nX] = poll.cnt + 1;
                    visited[nY][nX] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int[] ints : dist) {
            for (int i : ints) {
                sb.append(i + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static class Node {
        int y, x, cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }
}
