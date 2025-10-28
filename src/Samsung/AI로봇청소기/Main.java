package Samsung.AI로봇청소기;


/**
 * 1. 청소기 이동: 위, 왼, 오, 아래 순으로 bfs해서 오염된 격자 찾기
 * 2. 청소: 우선순위 거꾸로 돌며 방향을 select, 청소 진행
 * 3. 먼지 축적: 루프 돌며 ++
 * 4. 먼지 확산: 배열 하나 만들어서 확산
 * 시간 복잡도
 * 1. 청소기수 * N^2 = 50 * 30^2 = 45,000
 * 2. 청소기수 * 4 * 4 = 50 * 16 = 800
 * 3. N^2 = 900
 * 4. N^2 * 4 = 3,600
 * 1번의 루프에 약 50,000 -> 테스트 횟수 50 곱하면 250,000
 **/

import org.w3c.dom.Node;

import java.util.*;
import java.io.*;

// 1:09 start
public class Main {
    static int N, K, L;
    static int[][] map;
    static List<Pair> robots = new ArrayList<>();
    static StringBuilder result = new StringBuilder();
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};

    static int[] dir = {1, 0, 2, 3};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            robots.add(new Pair(y, x));
        }

//        print(map);
//        moveRobot();
//        clean();
//        System.out.println();
//        print(map);
//        add();
//        System.out.println();
//        print(map);
//        spread();
//        System.out.println();
//        print(map);
        while (L > 0) {
            moveRobot();
            clean();
            add();
            spread();
            int count = count();
            result.append(count).append("\n");
            L--;
//            print(map);
//            System.out.println("cnt = " + count);
//            System.out.println();
        }


        System.out.println(result.toString());
    }

    static int count() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    sum += map[i][j];
                }
            }
        }
        return sum;
    }

    static void spread() {
        int[][] tmp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) {
                    int sum = 0;
                    for (int k = 0; k < 4; k++) {
                        int nY = i + dy[k];
                        int nX = j + dx[k];
                        if (outOfRange(nY, nX) || map[nY][nX] <= 0) continue;
                        sum += map[nY][nX];
                    }
                    tmp[i][j] = sum / 10;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tmp[i][j] > 0) {
                    map[i][j] = tmp[i][j];
                }
            }
        }
    }

    static void add() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    map[i][j] += 5;
                }
            }
        }
    }


    static void clean() {
        for (Pair robot : robots) {
            int max = 0;
            int targetIdx = 0;

            for (int i = 0; i < 4; i++) {
                int tmp = 0;

                if (map[robot.y][robot.x] > 0) {
                    tmp += Math.min(20, map[robot.y][robot.x]);
                }
                for (int j = 0; j < 4; j++) {
                    int nY = robot.y + dy[j];
                    int nX = robot.x + dx[j];

                    if (j == dir[i] || outOfRange(nY, nX)) continue;
                    if (map[nY][nX] > 0) {
                        tmp += Math.min(20, map[nY][nX]);
                    }
                }

                if (tmp > max) {
                    max = tmp;
                    targetIdx = i;
                }
            }

            if (map[robot.y][robot.x] > 0) {
                map[robot.y][robot.x] = Math.max(0, map[robot.y][robot.x] - 20);
            }
            for (int i = 0; i < 4; i++) {
                int nY = robot.y + dy[i];
                int nX = robot.x + dx[i];

                if (i == dir[targetIdx] || outOfRange(nY, nX)) continue;
                if (map[nY][nX] > 0) {
                    map[nY][nX] = Math.max(0, map[nY][nX] - 20);
                }
            }
        }
    }

    static void moveRobot() {
        for (Pair robot : robots) {
            Pair target = bfs(robot);
            robot.y = target.y;
            robot.x = target.x;
        }
    }

    static Pair bfs(Pair robot) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Node(robot.y, robot.x, 0));
        visited[robot.y][robot.x] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
            if (n1.y != n2.y) {
                return n1.y - n2.y;
            } else {
                return n1.x - n2.x;
            }
        });

        while (!q.isEmpty()) {
            Node poll = q.poll();

            if (map[poll.y][poll.x] > 0) {
                if (pq.isEmpty() || pq.peek().depth == poll.depth) {
                    pq.add(poll);
                } else {
                    break;
                }
            }

            loop:
            for (int i = 0; i < 4; i++) {
                int nY = poll.y + dy[i];
                int nX = poll.x + dx[i];
                if (outOfRange(nY, nX) || visited[nY][nX]) continue;
                if (map[nY][nX] < 0) continue;
                for (Pair pair : robots) {
                    if (pair.y == nY && pair.x == nX) continue loop;
                }
                q.add(new Node(nY, nX, poll.depth + 1));
                visited[nY][nX] = true;
            }
        }

        if (pq.isEmpty()) {
            return robot;
        } else {
            Node poll = pq.poll();
            return new Pair(poll.y, poll.x);
        }
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }


    static void print(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    static class Node {
        int y, x, depth;

        public Node(int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}