package Samsung.팩맨;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * CheckPoint
 * 1. 역순으로 탐색해야 eat 값이 갱신되지 않았을 때(팩맨이 64개 경우 중 어디로 가도 못잡아먹을 때) 경로가 0, 0, 0, 0으로 고정되는 걸 막을 수 있었음.
 * 2. 백트래킹으로 64개의 경로를 탐색할 때, boolean[][] visited 로 하니까 정상 동작이 안됨.
 *  -> 경로를 여러번 왔다갔다할 수 있으니까 true, false 가 아닌 방문횟수를 카운팅해줘야함
 *  -> int[][]로 변경해서 해결
 *  -> 2번에서 두 시간정도 소요;;;;
 */
public class Main3 {
    static List<Integer>[][] diedMap, monsterMap, tmpMap;
    static Pair packMan;
    static int[] dy8 = {-1, -1, 0, 1, 1, 1, 0, -1}; // ↑, ↖, ←, ↙, ↓, ↘, →, ↗
    static int[] dx8 = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy4 = {-1, 0, 1, 0}; // ↑, ←, ↓, →
    static int[] dx4 = {0, -1, 0, 1};
    static int[] routes;
    static int m, t, maxEat;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/Samsung/팩맨/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int pY = Integer.parseInt(st.nextToken()) - 1;
        int pX = Integer.parseInt(st.nextToken()) - 1;
        packMan = new Pair(pY, pX);

        monsterMap = new ArrayList[4][4];
        tmpMap = new ArrayList[4][4];
        diedMap = new ArrayList[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                monsterMap[i][j] = new ArrayList<>();
                tmpMap[i][j] = new ArrayList<>();
                diedMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;
            monsterMap[r][c].add(d);
        }

        for (int k = 0; k < t; k++) {
            step1_CopyMonster();
            step2_MoveMonster();
            step3_MovePack();
            step4_RemoveDied();
            step5_CopyMonster();
        }
        System.out.println(countMonster());
    }

    static void step1_CopyMonster() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (monsterMap[i][j].size() > 0) {
                    for (Integer dir : monsterMap[i][j]) {
                        tmpMap[i][j].add(dir);
                    }
                }
            }
        }
    }

    static void step2_MoveMonster() {
        List<Integer>[][] tmpList = new ArrayList[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tmpList[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (monsterMap[i][j].size() > 0) {
                    for (Integer dir : monsterMap[i][j]) {
                        boolean move = false;
                        for (int k = 0; k < 8; k++) {
                            int nextY = i + dy8[(dir + k) % 8];
                            int nextX = j + dx8[(dir + k) % 8];
                            if (nextY < 0 || nextX < 0 || nextY >= 4 || nextX >= 4) continue;
                            if (diedMap[nextY][nextX].size() > 0 || (nextY == packMan.y && nextX == packMan.x)) continue;
                            tmpList[nextY][nextX].add((dir + k) % 8);
                            move = true;
                            break;
                        }
                        if (!move) {
                            tmpList[i][j].add(dir);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                monsterMap[i][j].clear();
                if (tmpList[i][j].size() > 0) {
                    for (Integer dir : tmpList[i][j]) {
                        monsterMap[i][j].add(dir);
                    }
                }
            }
        }
    }

    static void step3_MovePack() {
        routes = new int[3];
        maxEat = 0;
        selectRoute(0, 0, packMan.y, packMan.x, new int[3], new int[4][4]);

        for (int i = 0; i < 3; i++) {
            packMan.y += dy4[routes[i]];
            packMan.x += dx4[routes[i]];
            int size = monsterMap[packMan.y][packMan.x].size();
            monsterMap[packMan.y][packMan.x].clear();
            for (int j = 0; j < size; j++) {
                diedMap[packMan.y][packMan.x].add(3);
            }
        }
    }

    static void selectRoute(int cnt, int eat, int curY, int curX, int[] choice, int[][] visited) {
        if (cnt == 3) {
            if (eat >= maxEat) {
                maxEat = eat;
                for (int i = 0; i < 3; i++) {
                    routes[i] = choice[i];
                }
            }
            return;
        }
        for (int i = 3; i >= 0; i--) {
            int nextY = curY + dy4[i];
            int nextX = curX + dx4[i];
            if (nextY < 0 || nextX < 0 || nextY >= 4 || nextX >= 4) {
                continue;
            }
            int curEat = 0;
            if(visited[nextY][nextX] == 0) curEat += monsterMap[nextY][nextX].size();
            visited[nextY][nextX] += 1;
            choice[cnt] = i;
            selectRoute(cnt + 1, eat + curEat, nextY, nextX, choice, visited);
            visited[nextY][nextX] -= 1;
        }
    }

    static void step4_RemoveDied() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (diedMap[i][j].size() > 0) {
                    for (int k = 0; k < diedMap[i][j].size(); k++) {
                        diedMap[i][j].set(k, diedMap[i][j].get(k) - 1);
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (diedMap[i][j].size() > 0) {
                    Iterator<Integer> iterator = diedMap[i][j].iterator();
                    while (iterator.hasNext()) {
                        if (iterator.next() == 0) {
                            iterator.remove();
                        }
                    }
                }
            }
        }
    }

    static void step5_CopyMonster() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tmpMap[i][j].size() > 0) {
                    for (Integer dir : tmpMap[i][j]) {
                        monsterMap[i][j].add(dir);
                    }
                }
                tmpMap[i][j].clear();
            }
        }
    }

    static int countMonster() {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (monsterMap[i][j].size() > 0) {
                    cnt += monsterMap[i][j].size();
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
