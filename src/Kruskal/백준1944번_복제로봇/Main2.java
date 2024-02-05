package Kruskal.백준1944번_복제로봇;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main2 {
    static int N, M;
    static char[][] map;
    static int[][] numMap;
    static int[] parent;
    static List<Pair> list;
    static PriorityQueue<Connection> pq;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static Queue<Node> q = new LinkedList<>();
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        pq = new PriorityQueue<>();
        map = new char[N][N];
        numMap = new int[N][N];
        parent = new int[M + 1];
        visited = new boolean[N][N];

        for (int i = 0; i < M + 1; i++) {
            parent[i] = i;
        }

        int numCnt = 0;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'S' || map[i][j] == 'K') {
                    list.add(new Pair(i, j));
                    numMap[i][j] = numCnt;
                    numCnt++;
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = i; j < list.size(); j++) {
                bfs(list.get(i), list.get(j));
            }
        }

//        while (!pq.isEmpty()) {
//            Connection poll = pq.poll();
//            System.out.println(poll.v1 + ", " + poll.v2 + ": " + poll.cost);
//        }

        int result = 0;
        int cnt = 0;
        boolean isFinish = false;

        while (!pq.isEmpty()) {
            if (cnt == M) {
                isFinish = true;
                break;
            }
            Connection poll = pq.poll();
            if (find(poll.v1) != find(poll.v2)) {
                cnt++;
                result += poll.cost;
                union(poll.v1, poll.v2);
            }
        }

        if (isFinish) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }


    }

    static void bfs(Pair start, Pair end) {
        q.clear();
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        q.add(new Node(start.y, start.x, 0));

        while (!q.isEmpty()) {
            Node poll = q.poll();
            visited[poll.y][poll.x] = true;

            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (visited[nY][nX]) continue;
                if (map[nY][nX] == '1') continue;
                if (nY == end.y && nX == end.x) {
                    pq.add(new Connection(numMap[start.y][start.x], numMap[end.y][end.x], poll.move + 1));
                    return;
                } else {
                    q.add(new Node(nY, nX, poll.move + 1));
                }
            }
        }
    }

    static void union(int y, int x) {
        int a = find(y);
        int b = find(x);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }


    static class Connection implements Comparable<Connection> {
        int v1, v2, cost;

        public Connection(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connection o) {
            return this.cost - o.cost;
        }
    }


    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Node {
        int y, x, move;

        public Node(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }
}
