package DFSBFS.벽부수고이동하기4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map, result;
    static Group[][] groups;
    static boolean[][] visited;
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        groups = new Group[N][M];
        result = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        findGroups();
        makeResult();

        StringBuilder sb = new StringBuilder();

        for (int[] ints : result) {
            for (int i : ints) {
                sb.append(i);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void makeResult() {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    set.clear();
                    for (int k = 0; k < 4; k++) {
                        int nY = i + dy[k];
                        int nX = j + dx[k];
                        if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                        if (map[nY][nX] == 0 && !set.contains(groups[nY][nX].group)) {
                            result[i][j] += groups[nY][nX].cnt;
                            set.add(groups[nY][nX].group);
                        }
                    }
                    result[i][j] += 1;
                    result[i][j] %= 10;
                } else {
                    result[i][j] = 0;
                }
            }
        }
    }

    private static void findGroups() {
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && groups[i][j] == null) {
                    bfs(i, j, num);
                    num++;
                }
            }
        }
    }

    private static void bfs(int i, int j, int num) {
        Queue<Node> q = new LinkedList<>();
        List<Node> list = new ArrayList<>();
        list.add(new Node(i, j));
        q.add(new Node(i, j));
        visited[i][j] = true;

        while (!q.isEmpty()) {
            Node poll = q.poll();

            for (int k = 0; k < 4; k++) {
                int nY = poll.y + dy[k];
                int nX = poll.x + dx[k];
                if (nY < 0 || nX < 0 || nY >= N || nX >= M) continue;
                if (!visited[nY][nX] && map[nY][nX] == 0) {
                    visited[nY][nX] = true;
                    q.add(new Node(nY, nX));
                    list.add(new Node(nY, nX));
                }
            }
        }

        for (Node node : list) {
            groups[node.y][node.x] = new Group(num, list.size());
        }
    }

    static class Group {
        int group, cnt;

        public Group(int group, int cnt) {
            this.group = group;
            this.cnt = cnt;
        }
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }


}
