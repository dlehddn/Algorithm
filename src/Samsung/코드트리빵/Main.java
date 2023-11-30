package Samsung.코드트리빵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[][] map;
    private static Pair[] wantStore;
    private static ArrayList<Pair>[] bfsGroup;
    private static Pair[] curLocation;
    private static ArrayList<Integer> onMapList = new ArrayList<>();
    private static int[] dy = {-1, 0, 0, 1};
    private static int[] dx = {0, -1, 1, 0};
    private static boolean[][] visitedStore;
    private static boolean[][] visitedCamp;
    private static ArrayList<Integer> arriveList = new ArrayList<>();
    private static Pair[] baseCamp;
    private static Stack<Pair> tmpStore = new Stack<>();
    private static Stack<Pair> tmpCamp = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        wantStore = new Pair[M + 1];
        bfsGroup = new ArrayList[M + 1];
        curLocation = new Pair[M + 1];
        visitedStore = new boolean[N][N];
        visitedCamp = new boolean[N][N];
        baseCamp = new Pair[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            wantStore[i] = new Pair(y, x);
            bfsGroup[i] = new ArrayList<>();
        }

        int count = 1;
        while (true) {
            for (int i = 0; i < onMapList.size(); i++) {
                step1_Move(onMapList.get(i));
            }
            step2_blockStore();
            if (count <= M) {
                step3_newPlayer(count);
            }
            if (arriveList.size() == M) {
                System.out.println(count);
                break;
            }
            count++;

        }
    }

    private static void step1_Move(int who) {

        if (arriveList.contains(who)) {
            return;
        }
        if (!onMapList.contains(who)) {
            return;
        }
        if (bfsGroup[who].size() == 0) {
            bfs(who, wantStore[who]);
            curLocation[who].y = bfsGroup[who].get(0).y;
            curLocation[who].x = bfsGroup[who].get(0).x;
            bfsGroup[who].remove(0);
            if (curLocation[who].y == wantStore[who].y && curLocation[who].x == wantStore[who].x) {
                tmpStore.push(new Pair(curLocation[who].y, curLocation[who].x));
                arriveList.add(who);
            }
        }
        else if (bfsGroup[who].size() > 0) {
            if (canGO(who, bfsGroup[who])) {
                curLocation[who].y = bfsGroup[who].get(0).y;
                curLocation[who].x = bfsGroup[who].get(0).x;
                bfsGroup[who].remove(0);
                if (curLocation[who].y == wantStore[who].y && curLocation[who].x == wantStore[who].x) {
                    tmpStore.push(new Pair(curLocation[who].y, curLocation[who].x));
                    arriveList.add(who);
                }
            } else {
                bfs(who, wantStore[who]);
                curLocation[who].y = bfsGroup[who].get(0).y;
                curLocation[who].x = bfsGroup[who].get(0).x;
                bfsGroup[who].remove(0);
            }
        }

    }

    private static boolean canGO(int who, ArrayList<Pair> route) {
        for (Pair curRoute : route) {
            if (visitedCamp[curRoute.y][curRoute.x]) {
                if (baseCamp[who].x != curRoute.x || baseCamp[who].y != curRoute.y) {
                    continue;
                }
                return false;
            }
            if (visitedStore[curRoute.y][curRoute.x]) {
                return false;
            }
        }
        return true;
    }

    private static void bfs(int who, Pair destination) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        ArrayList<Pair>[][] routes = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                routes[i][j] = new ArrayList<>();
            }
        }
        Pair curLocation = Main.curLocation[who];
        visited[curLocation.y][curLocation.x] = true;
        q.add(curLocation);

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = poll.y + dy[i];
                int nextX = poll.x + dx[i];
                if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
                    continue;
                }
                if (visited[nextY][nextX] || visitedCamp[nextY][nextX] || visitedStore[nextY][nextX]) {
                    continue;
                }
                visited[nextY][nextX] = true;
                q.add(new Pair(nextY, nextX));
                for (Pair cur : routes[poll.y][poll.x]) {
                    routes[nextY][nextX].add(cur);
                }
                routes[nextY][nextX].add(new Pair(nextY, nextX));
                if (destination.x == nextX && destination.y == nextY) {
                    bfsGroup[who] = routes[nextY][nextX];
                    return;
                }
            }
        }
    }

    ////////////////// step 2 시작

    private static void step2_blockStore() {
        while (!tmpStore.isEmpty()) {
            Pair pop = tmpStore.pop();
            visitedStore[pop.y][pop.x] = true;
        }
    }

    ////////////////  step 3 시작


    private static void step3_newPlayer(int who) {
        bfs_Step3(who);
    }

    private static void bfs_Step3(int who) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        Pair curLoc = wantStore[who];
        visited[curLoc.y][curLoc.x] = true;
        q.add(curLoc);

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = poll.y + dy[i];
                int nextX = poll.x + dx[i];
                if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
                    continue;
                }
                if (visited[nextY][nextX] || visitedCamp[nextY][nextX] || visitedStore[nextY][nextX]) {
                    continue;
                }
                visited[nextY][nextX] = true;
                q.add(new Pair(nextY, nextX));
                if (map[nextY][nextX] == 1) {
                    baseCamp[who] = new Pair(nextY, nextX);
                    visitedCamp[nextY][nextX] = true;
                    onMapList.add(who);
                    curLocation[who] = new Pair(nextY, nextX);
                    return;
                }
            }
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
}
