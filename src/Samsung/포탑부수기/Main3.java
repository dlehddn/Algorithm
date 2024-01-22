package Samsung.포탑부수기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 디버깅 한 부분
 * 1. 공격자 선정된 턴을 각자 관리하는 부분에서, 단순히 ++ 연산이 아니라 turn + 1을 해줘야 논리적으로 맞음
 * 2. 공격자 선정 우선순위에서, 열 값이란 내 코드에서 y 값이 아니라 x 값을 의미함
 * -> 이거 처음부터 애매한 줄 알았으면 체크 해놓고 시작했어야지
 */
public class Main3 {

    static Turret[][] map;
    static Pair attacker, depender;
    static int[][] repairMap;
    static int N, M, K;
    static PriorityQueue<Turret> aPq, dPq;
    static int[] dy4 = {0, 1, 0, -1};
    static int[] dx4 = {1, 0, -1, 0};
    static int[] dy8 = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dx8 = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/Samsung/포탑부수기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new Turret[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = new Turret(i, j, Integer.parseInt(st.nextToken()), 0);
            }
        }
        aPq = new PriorityQueue<>((t1, t2) -> {
            if (t1.power != t2.power) {
                return Integer.compare(t1.power, t2.power);
            } else {
                if (t1.when != t2.when) {
                    return Integer.compare(t2.when, t1.when);
                } else {
                    if (t1.y + t1.x != t2.y + t2.x) {
                        return Integer.compare(t2.y + t2.x, t1.y + t1.x);
                    } else {
                        return Integer.compare(t2.x, t1.x);
                    }
                }
            }
        });
        dPq = new PriorityQueue<>((t1, t2) -> {
            if (t1.power != t2.power) {
                return Integer.compare(t2.power, t1.power);
            } else {
                if (t1.when != t2.when) {
                    return Integer.compare(t1.when, t2.when);
                } else {
                    if (t1.y + t1.x != t2.y + t2.x) {
                        return Integer.compare(t1.y + t1.x, t2.y + t2.x);
                    } else {
                        return Integer.compare(t1.x, t2.x);
                    }
                }
            }
        });

        for (int i = 0; i < K; i++) {
            step1_selectAttacker(i);
            step2_selectDepender();
            if (!step3_laserAttack()) {
                step3_bombAttack();
            }
            step4_repair();
            if (isEnd()) {
                break;
            }
        }
        System.out.println(findMax());
    }

    static void step1_selectAttacker(int turn) {
        aPq.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].power > 0) {
                    aPq.add(map[i][j]);
                }
            }
        }
        Turret poll = aPq.poll();
        poll.when = turn + 1;
        attacker = new Pair(poll.y, poll.x);
        map[attacker.y][attacker.x].power += N + M;
    }

    static void step2_selectDepender() {
        dPq.clear();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].power > 0) {
                    if (attacker.y != i || attacker.x != j) {
                        dPq.add(map[i][j]);
                    }
                }
            }
        }
        Turret poll = dPq.poll();
        depender = new Pair(poll.y, poll.x);
    }

    static boolean step3_laserAttack() {
        boolean laserAvailable = false;
        List<Pair> routes = new ArrayList<>();
        repairMap = new int[N][M];
        Queue<Routes> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new Routes(attacker.y, attacker.x, new ArrayList<>()));

        loop:
        while (!q.isEmpty()) {
            Routes poll = q.poll();
            visited[poll.y][poll.x] = true;
            for (int i = 0; i < 4; i++) {
                int nY = (poll.y + dy4[i] + N) % N;
                int nX = (poll.x + dx4[i] + M) % M;
                if (map[nY][nX].power == 0) continue;
                // 이전 경로 복사
                List<Pair> cur = new ArrayList<>();
                for (Pair route : poll.routes) {
                    cur.add(new Pair(route.y, route.x));
                }
                // 도착지면 break;
                if (depender.y == nY && depender.x == nX) {
                    routes = cur;
                    laserAvailable = true;
                    break loop;
                }
                // 아니면 다음 경로도 넣어주고 대기열에 add
                cur.add(new Pair(nY, nX));
                if (!visited[nY][nX]) {
                    q.add(new Routes(nY, nX, cur));
                }
            }
        }

        if (!laserAvailable) return false;
        else {
            for (Pair route : routes) {
                map[route.y][route.x].power = Math.max(0,
                        map[route.y][route.x].power - map[attacker.y][attacker.x].power / 2);
                repairMap[route.y][route.x] = 1;
            }
            map[depender.y][depender.x].power = Math.max(0,
                    map[depender.y][depender.x].power - map[attacker.y][attacker.x].power);
            repairMap[attacker.y][attacker.x] = 1;
            repairMap[depender.y][depender.x] = 1;
            return true;
        }

    }

    static void step3_bombAttack() {
        repairMap[attacker.y][attacker.x] = 1;
        repairMap[depender.y][depender.x] = 1;

        map[depender.y][depender.x].power = Math.max(0,
                map[depender.y][depender.x].power - map[attacker.y][attacker.x].power);
        for (int i = 0; i < 8; i++) {
            int nY = (depender.y + dy8[i] + N) % N;
            int nX = (depender.x + dx8[i] + M) % M;
            if (map[nY][nX].power == 0) continue;
            if (attacker.y == nY && attacker.x == nX) continue;
            repairMap[nY][nX] = 1;
            map[nY][nX].power = Math.max(0, map[nY][nX].power - map[attacker.y][attacker.x].power / 2);
        }
    }

    static void step4_repair() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (repairMap[i][j] != 1 && map[i][j].power != 0) {
                    map[i][j].power++;
                }
            }
        }
    }

    static int findMax() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].power > max) {
                    max = map[i][j].power;
                }
            }
        }
        return max;
    }

    static boolean isEnd() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].power > 0) {
                    cnt++;
                }
                if (cnt > 1) return false;
            }
        }
        return true;

    }

    static class Turret {
        int y;
        int x;
        int power;
        int when;

        public Turret(int y, int x, int power, int when) {
            this.y = y;
            this.x = x;
            this.power = power;
            this.when = when;
        }
    }

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Routes {
        int y;
        int x;
        List<Pair> routes;

        public Routes(int y, int x, List<Pair> routes) {
            this.y = y;
            this.x = x;
            this.routes = routes;
        }
    }
}
