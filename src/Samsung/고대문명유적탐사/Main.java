package Samsung.고대문명유적탐사;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 실수한 포인트
 * 1. bfs() 돌면서 같은 종류가 연결되어 있는게 몇 개인지 셀 때, poll.cnt + 1로 하면 전체 개수가 안나오지.
 * -> 하나씩 poll 할 때 마다 cnt++ 하는 식으로 계산해야 올바르다.
 * 2. 격자 3번 회전하면서 파괴되는 개수 확인할 때, 적은 회전 횟수가 우선순위라고 break 거는게 아니지.
 * -> 여러번 회전했을 때 파괴되는 개수가 늘어날 수 도 있으니까 ㅇㅇ 이건 당연한거
 * 풀이시간 : 약 3h
 */
public class Main {

    static int K, M, result;
    static int[][] map = new int[5][5];
    static Queue<Integer> q = new LinkedList<>();
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            q.add(Integer.parseInt(st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            result = 0;
            if (!step1()) break;
            step2();
            sb.append(result + " ");
        }
        System.out.println(sb.toString());
    }

    static boolean step1() {
        List<Info> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        // 중심격자가 가능한 9개의 좌표 탐색
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                int[][] copied = deepCopy(5, map);
                int[][] mini = make3X3(new Pair(i, j), copied);
                // 격자 3번 회전
                for (int k = 1; k <= 3; k++) {
                    mini = rotate(mini);
                    register(copied, mini, new Pair(i, j));
                    int score = bfs(copied);
                    if (score >= max && score != 0) {
                        if (score > max) {
                            list.clear();
                            max = score;
                        }
                        list.add(new Info(i, j, k));
                    }
                }

            }
        }
        if (list.size() == 0) return false;

        list.sort((i1, i2) -> {
            if (i1.rCnt != i2.rCnt) {
                return i1.rCnt - i2.rCnt;
            } else if (i1.x != i2.x) {
                // 열이 가장 작은 구간 먼저 선정에 유의, 반대로 생각할 수 있으니 체크
                return i1.x - i2.x;
            } else {
                return i1.y - i2.y;
            }
        });
        Info selected = list.get(0);
        int[][] copied = deepCopy(5, map);
        int[][] mini = make3X3(new Pair(selected.y, selected.x), copied);
        for (int i = 1; i <= selected.rCnt; i++) {
            mini = rotate(mini);
        }
        register(map, mini, new Pair(selected.y, selected.x));
        return true;
    }

    static int[][] deepCopy(int size, int[][] origin) {
        int[][] copied = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                copied[i][j] = origin[i][j];
            }
        }
        return copied;
    }

    static int[][] make3X3(Pair center, int[][] origin) {
        int[][] mini = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                mini[i][j] = origin[center.y + (i - 1)][center.x + (j - 1)];
            }
        }
        return mini;
    }

    static int[][] rotate(int[][] mini) {
        int[][] copied = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copied[i][j] = mini[2 - j][i];
            }
        }
        return copied;
    }

    static void register(int[][] origin, int[][] mini, Pair center) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                origin[center.y + (i - 1)][center.x + (j - 1)] = mini[i][j];
            }
        }
    }

    static int bfs(int[][] map) {
        int cnt = 0;
        Queue<Node> q;
        boolean[][] visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int curCnt = 0;
                if (visited[i][j]) continue;
                q = new LinkedList<>();
                q.add(new Node(i, j, 1));
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    Node poll = q.poll();
                    curCnt++;
                    for (int k = 0; k < 4; k++) {
                        int nY = poll.y + dy[k];
                        int nX = poll.x + dx[k];
                        if (outOfRange(nY, nX) || visited[nY][nX]) continue;
                        if (map[poll.y][poll.x] != map[nY][nX]) continue;
                        q.add(new Node(nY, nX, poll.cnt + 1));
                        visited[nY][nX] = true;
                    }
                }
                if (curCnt >= 3) {
                    cnt += curCnt;
                }
            }
        }
        return cnt;
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= 5 || x >= 5;
    }

    static void step2() {
        int cnt = bfsAndRemove();
        if (cnt == 0) return;
        result += cnt;
        fill();
        step2();
    }

    static int bfsAndRemove() {
        int result = 0;
        Queue<Pair> q;
        boolean[][] visited = new boolean[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (visited[i][j]) continue;
                List<Pair> pairs = new ArrayList<>();
                q = new LinkedList<>();
                q.add(new Pair(i, j));
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    Pair poll = q.poll();
                    pairs.add(poll);

                    for (int k = 0; k < 4; k++) {
                        int nY = poll.y + dy[k];
                        int nX = poll.x + dx[k];
                        if (outOfRange(nY, nX) || visited[nY][nX]) continue;
                        if (map[poll.y][poll.x] != map[nY][nX]) continue;
                        q.add(new Pair(nY, nX));
                        visited[nY][nX] = true;
                    }

                }

                if (pairs.size() >= 3) {
                    for (Pair p : pairs) {
                        map[p.y][p.x] = 0;
                        result++;
                    }
                }
            }
        }
        return result;
    }

    static void fill() {
        for (int j = 0; j < 5; j++) {
            for (int i = 4; i >= 0; i--) {
                if (map[i][j] == 0) {
                    map[i][j] = q.poll();
                }
            }
        }
    }

    static class Node {
        int y, x, cnt;

        public Node(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Info {
        int y, x, rCnt;

        public Info(int y, int x, int rCnt) {
            this.y = y;
            this.x = x;
            this.rCnt = rCnt;
        }
    }
}
