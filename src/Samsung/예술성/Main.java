package Samsung.예술성;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 배열 내부를 쪼개서 회전시키는데 특화된 문제. 연습하는데 많은 도움이 됐다.
 * 기억하자! 항상 회전시킬 배열은 기준을 (0,0)으로 옮겨놓고 해야 편하다.
 */
public class Main {

    static ArrayList<Integer> cntList;
    static ArrayList<Integer> originList;
    static int[][] matchMap;
    static int[][] map;
    static int[][] newMap;
    static boolean[][] visited;
    static int N;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int totalScore;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        while (count < 4) {
            step1_CalculateScore();
            step2_Spin();
            count++;
        }
        System.out.println(totalScore/2);
        ///////// 테스트 공간 //////////
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//        step1_CalculateScore();
//        step2_Spin();
//        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//
//        System.out.println("cntSize = " + cntList.size());
//        for (Integer integer : cntList) {
//            System.out.println("순서대로 : " + integer);
//        }
//        System.out.println("total = " + totalScore);

        ///////////////////////////
    }

    static void step1_CalculateScore() {

        cntList = new ArrayList<>();
        originList = new ArrayList<>();
        visited = new boolean[N][N];
        newMap = new int[N][N];
        int groupId = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j, groupId, map[i][j]);
                    groupId++;
                }
            }
        }
        // 여기까지 newMap에 그룹 ID 기준으로 정렬 완료

        matchMap = new int[cntList.size()][cntList.size()];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int curGID = newMap[i][j];
                for (int k = 0; k < 4; k++) {
                    int nextY = i + dy[k];
                    int nextX = j + dx[k];
                    if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
                        continue;
                    }
                    if (newMap[nextY][nextX] != curGID) {
                        matchMap[curGID][newMap[nextY][nextX]] += 1;
                    }
                }
            }
        }
        // 여기까지가 그룹 사이즈로 만든 베열

        for (int i = 0; i < cntList.size(); i++) {
            for (int j = 0; j < cntList.size(); j++) {
                totalScore += matchMap[i][j] * (cntList.get(i) + cntList.get(j)) * originList.get(i) * originList.get(j);
            }
        }
    }

    static void bfs(int curY, int curX, int groupId, int color) {
        cntList.add(0);
        originList.add(map[curY][curX]);
        Queue<Pair> q = new LinkedList<>();
        int count;
        q.add(new Pair(curY, curX));
        visited[curY][curX] = true;
        count = 1;
        newMap[curY][curX] = groupId;

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nextY = poll.y + dy[i];
                int nextX = poll.x + dx[i];
                if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N) {
                    continue;
                }
                if (visited[nextY][nextX] || map[nextY][nextX] != color) {
                    continue;
                }
                visited[nextY][nextX] = true;
                q.add(new Pair(nextY, nextX));
                count++;
                newMap[nextY][nextX] = groupId;
            }
        }
        cntList.set(groupId, count);
    }

    static void step2_Spin() {
        centerSpin();
        sideSpin();
    }

    static void centerSpin() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            tmp[N / 2][i] = map[i][N / 2];
            tmp[N - i - 1][N / 2] = map[N / 2][i];
        }

        for (int i = 0; i < N; i++) {
            map[N / 2][i] = tmp[N / 2][i];
            map[i][N / 2] = tmp[i][N / 2];
        }
    }

    static void sideSpin() {
        int size = N / 2;
        int[][] tmp = new int[N][N];
        int[][] tmp2 = new int[N][N];
        int[][] tmp3 = new int[N][N];
        int[][] tmp4 = new int[N][N];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp2[i][j] = map[i][j + size + 1];
                tmp3[i][j] = map[i + size + 1][j];
                tmp4[i][j] = map[i + size + 1][j + size + 1];
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[i][j] = map[size - j - 1][i];
                tmp[i][j + size + 1] = tmp2[size - j - 1][i];
                tmp[i + size + 1][j] = tmp3[size - j - 1][i];
                tmp[i + size + 1][j + size + 1] = tmp4[size - j - 1][i];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == size || j == size) {
                    continue;
                }
                map[i][j] = tmp[i][j];
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
