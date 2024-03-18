package Dijkstra.백준7569번_토마토;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int[][][] tomato;
    static int N, M, H;
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 0, -1, 1};
    static int[] dz = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        tomato = new int[N][M][H];
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    tomato[j][k][i] = Integer.parseInt(st.nextToken());
                }
            }
        }
        System.out.println(dijkstra());


    }

    static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.days));
        boolean[][][] visited = new boolean[N][M][H];
        boolean exit = true;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tomato[j][k][i] == 1) {
                        pq.add(new Node(j, k, i, 0));
                    } else if (tomato[j][k][i] == 0) {
                        exit = false;
                    }
                }
            }
        }
        if (exit) return 0;

        while (!pq.isEmpty()) {
            Node poll = pq.poll();
            if (visited[poll.y][poll.x][poll.z]) continue;
            visited[poll.y][poll.x][poll.z] = true;

            for (int i = 0; i < 6; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                int nZ = poll.z + dz[i];

                if (nY < 0 || nX < 0 || nZ < 0 || nY >= N || nX >= M || nZ >= H) continue;
                if (!visited[nY][nX][nZ] && tomato[nY][nX][nZ] == 0) {
                    tomato[nY][nX][nZ] = poll.days + 1;
                    pq.add(new Node(nY, nX, nZ, poll.days + 1));
                }
            }
        }
        return findDays();
    }

    private static int findDays() {
        int max = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (tomato[j][k][i] == 0) {
                        return -1;
                    } else if (tomato[j][k][i] > max) {
                        max = tomato[j][k][i];
                    }
                }
            }
        }
        return max;
    }

    static class Node {
        int y, x, z, days;

        public Node(int y, int x, int z, int days) {
            this.y = y;
            this.x = x;
            this.z = z;
            this.days = days;
        }
    }
}
