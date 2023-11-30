package Samsung.포탑부수기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {
    /**
     *
     * 시간 계속 잡아먹은 이유 : 더 옛날에 선택된 포탑을 디펜더로 선정할 때 너무 단순하게 생각함
     * attackerList에 계속 쌓아놓기만 하면, 인덱스 0 부터 찾아가도 그게 더 옛날에 선택된게 아닐 수 있음 --> 나중에 또 나왔을 수 있기 떄문에
     * 그래서 넣을 때 마다 앞에 있는애를 지워줬다!
     */

    static Pair attacker = new Pair(-1, -1);
    static Pair depender = new Pair(-1, -1);
    static int[][] map;
    static ArrayList<Pair> attackerList = new ArrayList<>();
    static int[][] firstAttacker;
    static int N, M, K;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] dx8 = {1, 0, -1, 0, 1, 1, -1, -1};
    static int[] dy8 = {0, 1, 0, -1, 1, -1, 1, -1};
    static boolean isRaiser = false;
    static int[][] relatedMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        firstAttacker = new int[N][M];
        relatedMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                firstAttacker[i][j] = 1;
                map[i][j] = Integer.parseInt(st.nextToken());
            }

        }
        // relatedList 초기화부터 시작
        while (K > 0) {
            K--;
            relatedMap = new int[N][M];
            step1_selectAttacker();
            for (int i = 0; i < attackerList.size(); i++) {
                Pair pair = attackerList.get(i);
                if (pair.x == attacker.x && pair.y == attacker.y) {
                    attackerList.remove(pair);
                }
            }
            attackerList.add(new Pair(attacker.y, attacker.x));
            step2_selectDepender();
            step3_attack();
            step4_pointPlus();
            if (findRemain() == 1) {
                break;
            }
        }
        System.out.println(findMax());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    static void step1_selectAttacker() {
        ArrayList<Pair> tmp = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                if (map[i][j] == min) {
                    tmp.add(new Pair(i, j));
                } else if (map[i][j] < min) {
                    tmp.clear();
                    tmp.add(new Pair(i, j));
                    min = map[i][j];
                }
            }
        }
        // 여기까지 최하 공격력 그룹 찾기
        if (tmp.size() == 1) {
            Pair cur = tmp.get(0);
            attacker.x = cur.x;
            attacker.y = cur.y;
            if (firstAttacker[cur.y][cur.x] == 1) {
                firstAttacker[cur.y][cur.x] = 0;
            }
            map[attacker.y][attacker.x] += N + M;
            relatedMap[attacker.y][attacker.x] = 1;
        } else if (tmp.size() > 1) {

            for (int i = attackerList.size() - 1; i >= 0; i--) {
                Pair pair = attackerList.get(i);
                for (Pair cur : tmp) {
                    if (pair.x == cur.x && pair.y == cur.y) {
                        attacker.x = cur.x;
                        attacker.y = cur.y;
                        map[attacker.y][attacker.x] += N + M;
                        relatedMap[attacker.y][attacker.x] = 1;
                        return;
                    }
                }
            }

            for (int i = (N - 1) + (M - 1); i >= 0; i--) {
                for (int j = M - 1; j >= 0; j--) {
                    try {
                        if (firstAttacker[i - j][j] == 1) {
                            for (Pair pair : tmp) {
                                if (pair.x == j && pair.y == i - j) {
                                    attacker.x = pair.x;
                                    attacker.y = pair.y;
                                    firstAttacker[i - j][j] = 0;
                                    map[attacker.y][attacker.x] += N + M;
                                    relatedMap[attacker.y][attacker.x] = 1;
                                    return;
                                }
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
            }
        }
    }

    static void step2_selectDepender() {
        ArrayList<Pair> tmp = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i == attacker.y && j == attacker.x) {
                    continue;
                }
                if (map[i][j] == max) {
                    tmp.add(new Pair(i, j));
                } else if (map[i][j] > max) {
                    tmp.clear();
                    tmp.add(new Pair(i, j));
                    max = map[i][j];
                }
            }
        }
        // 여기까지 최고 공격력 그룹 찾기

        if (tmp.size() == 1) {
            Pair cur = tmp.get(0);
            depender.x = cur.x;
            depender.y = cur.y;
        } else if (tmp.size() > 1) {

            for (int i = 0; i <= (N - 1) + (M - 1); i++) {
                for (int j = 0; j < M; j++) {
                    try {
                        if (firstAttacker[i - j][j] == 1) {
                            for (Pair pair : tmp) {
                                if (pair.x == j && pair.y == i - j) {
                                    depender.x = pair.x;
                                    depender.y = pair.y;
                                    return;
                                }
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {

                    }
                }
            }

            for (int i = 0; i < attackerList.size(); i++) {
                Pair pair = attackerList.get(i);
                for (Pair cur : tmp) {
                    if (pair.x == cur.x && pair.y == cur.y) {
                        depender.x = cur.x;
                        depender.y = cur.y;
                        return;
                    }
                }
            }
        }
    }

    static void step3_attack() {
        isRaiser = true;
        raiser();
        if (!isRaiser) {
            bomb();
        }
    }

    static void raiser() {
        ArrayList<Pair>[][] tmp = new ArrayList[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmp[i][j] = new ArrayList<>();
            }
        }
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.add(attacker);
        visited[attacker.y][attacker.x] = true;

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = (poll.y + dy[i] + N) % N;
                int nextX = (poll.x + dx[i] + M) % M;
                if (map[nextY][nextX] == 0 || visited[nextY][nextX]) {
                    continue;
                }
                visited[nextY][nextX] = true;
                q.add(new Pair(nextY, nextX));
                for (Pair pair : tmp[poll.y][poll.x]) {
                    tmp[nextY][nextX].add(pair);
                }
                tmp[nextY][nextX].add(new Pair(nextY, nextX));
                if (nextY == depender.y && nextX == depender.x) {
                    for (Pair pair : tmp[depender.y][depender.x]) {
                        relatedMap[pair.y][pair.x] = 1;
                        if (pair.x == depender.x && pair.y == depender.y) {
                            map[depender.y][depender.x] -= map[attacker.y][attacker.x];
                            if (map[depender.y][depender.x] < 0) {
                                map[depender.y][depender.x] = 0;
                            }
                        } else {
                            map[pair.y][pair.x] -= map[attacker.y][attacker.x] / 2;
                            if (map[pair.y][pair.x] < 0) {
                                map[pair.y][pair.x] = 0;
                            }
                        }
                    }
                    isRaiser = true;
                    return;
                }
            }
        }
        isRaiser = false;
    }

    static void bomb() {
        map[depender.y][depender.x] -= map[attacker.y][attacker.x];
        relatedMap[depender.y][depender.x] = 1;
        if (map[depender.y][depender.x] < 0) {
            map[depender.y][depender.x] = 0;
        }

        for (int i = 0; i < 8; i++) {
            int nextY = (depender.y + dy8[i] + N) % N;
            int nextX = (depender.x + dx8[i] + M) % M;
            if (nextY == attacker.y && nextX == attacker.x) {
                continue;
            }
            if (map[nextY][nextX] > 0) {
                relatedMap[nextY][nextX] = 1;
                map[nextY][nextX] -= map[attacker.y][attacker.x] / 2;
                if (map[nextY][nextX] < 0) {
                    map[nextY][nextX] = 0;
                }
            }
        }
    }


    static void step4_pointPlus() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (relatedMap[i][j] == 0 && map[i][j] > 0) {
                    map[i][j] += 1;
                }
            }
        }
    }

    static int findMax() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > max) {
                    max = map[i][j];
                }
            }
        }
        return max;
    }

    static int findRemain() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    static class Pair {
        int y;
        int x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}