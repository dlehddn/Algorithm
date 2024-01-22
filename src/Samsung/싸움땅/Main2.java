package Samsung.싸움땅;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * gunMap, playerMap 을 활용
 */
public class Main2 {

    // 순서대로 상, 우, 하, 좌
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};
    static PriorityQueue<Integer>[][] gunMap;
    static ArrayList<Player>[][] playerMap;
    static int[] result;
    static Pair[] playerDir;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/Samsung/싸움땅/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        gunMap = new PriorityQueue[N][N];
        playerMap = new ArrayList[N][N];
        result = new int[M + 1];
        playerDir = new Pair[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                gunMap[i][j] = new PriorityQueue<>((n1, n2) -> Integer.compare(n2, n1));
                playerMap[i][j] = new ArrayList<>();
                int power = Integer.parseInt(st.nextToken());
                gunMap[i][j].add(power);
            }
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            playerMap[x][y].add(new Player(i, s, 0, d));
            playerDir[i] = new Pair(x, y);
        }

        for (int i = 0; i < K; i++) {
            for (int j = 1; j <= M; j++) {
                Pair pair = step1_movePlayer(j);
                step2(pair);
            }
        }

        Arrays.stream(result)
                .skip(1)
                .forEach(n -> System.out.print(n + " "));
    }

    static Pair step1_movePlayer(int playerNum) {
        Pair pair = playerDir[playerNum];
        Player player = playerMap[pair.y][pair.x].get(0);
        int nextY = pair.y + dy[player.dir];
        int nextX = pair.x + dx[player.dir];

        if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
            nextY = pair.y + dy[(player.dir + 2) % 4];
            nextX = pair.x + dx[(player.dir + 2) % 4];
            player.dir = (player.dir + 2) % 4;
        }

        playerMap[nextY][nextX].add(new Player(player.num, player.pPower, player.gPower, player.dir));
        playerDir[playerNum] = new Pair(nextY, nextX);
        playerMap[pair.y][pair.x].clear();

        return new Pair(nextY, nextX);
    }

    static void step2 (Pair pair) {
        if (!isFight(pair)) {
            step2_1_updateGun(pair);
        } else {
            step2_2_1_fight(pair);
        }
    }

    static void step2_1_updateGun(Pair pair) {
        getPowerfulGun(pair);
    }

    static void step2_2_1_fight(Pair pair) {
        Collections.sort(playerMap[pair.y][pair.x]);
        Player winner = playerMap[pair.y][pair.x].get(0);
        Player loser = playerMap[pair.y][pair.x].get(1);
        result[winner.num] += (winner.gPower + winner.pPower) - (loser.gPower + loser.pPower);

        step2_2_2_moveLoser(loser, pair);
        playerMap[pair.y][pair.x].remove(loser);

        step2_2_3_winner(pair);
    }

    static void step2_2_2_moveLoser(Player player, Pair pair) {
        gunMap[pair.y][pair.x].add(player.gPower);
        player.gPower = 0;

        while (true) {
            int nextY = pair.y + dy[player.dir];
            int nextX = pair.x + dx[player.dir];
            if (canEnter(new Pair(nextY, nextX))) {
                playerMap[nextY][nextX].add(new Player(player.num, player.pPower, player.gPower, player.dir));
                playerDir[player.num] = new Pair(nextY, nextX);
                getPowerfulGun(new Pair(nextY, nextX));
                return;
            }
            player.dir = (player.dir + 1) % 4;
        }
    }

    static void step2_2_3_winner(Pair pair) {
        getPowerfulGun(pair);
    }

    static boolean canEnter(Pair pair) {
        if(pair.x < 0 || pair.y < 0 || pair.x >= N || pair.y >= N) return false;
        if(playerMap[pair.y][pair.x].size() >= 1) return false;
        return true;
    }

    static Player getPowerfulGun(Pair pair) {
        Player player = playerMap[pair.y][pair.x].get(0);
        int maxInPq = gunMap[pair.y][pair.x].peek();
        int myGunPower = player.gPower;
        if (maxInPq > myGunPower) {
            gunMap[pair.y][pair.x].poll();
            gunMap[pair.y][pair.x].add(myGunPower);
            player.gPower = maxInPq;
        }
        return player;
    }

    static boolean isFight(Pair pair) {
        if (playerMap[pair.y][pair.x].size() == 2) {
            return true;
        } else {
            return false;
        }
    }

    static class Player implements Comparable<Player>{
        int num;
        int pPower;
        int gPower;
        int dir;

        public Player(int num, int pPower, int gPower, int dir) {
            this.num = num;
            this.pPower = pPower;
            this.gPower = gPower;
            this.dir = dir;
        }

        @Override
        public int compareTo(Player o) {
            int thisSum = this.pPower + this.gPower;
            int oSum = o.pPower + o.gPower;
            if (thisSum != oSum) {
                return oSum - thisSum;
            } else {
                return o.pPower - this.pPower;
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
