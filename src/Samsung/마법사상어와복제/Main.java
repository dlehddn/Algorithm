package Samsung.마법사상어와복제;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 격자위에 있는 물고기 수를 출력할 때 Long으로 출력하기
 *
 *
 *
 *
 *
 *
 * 시간초과! 다시풀어보자. (10/4)
 */
public class Main {
    // ←, ↖, ↑, ↗, →, ↘, ↓, ↙
    private static int[] dx8 = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy8 = {0, 0, -1, -1, -1, 0, 1, 1, 1};

    //상, 하, 좌, 우
    private static int[] dx4 = {0, -1, 0, 1};
    private static int[] dy4 = {-1, 0, 1, 0};

    private static ArrayList<Integer>[][] fish;  // Integer는 각 물고기의 방향을 의미
    private static int[][][] smell;
    private static ArrayList<Integer> sharkTemp = new ArrayList<>();

    private static int N, M, S, sharkY, sharkX, result;
    private static ArrayList<int[]> sharkEat = new ArrayList<>();

    private static Stack<int[]> fishTemp = new Stack<>();
    private static Queue<int[]> fishList = new LinkedList<>(); // 물고기 여러마리면 중복가능하니까 이거 처리하기!

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        result = 0;
        N = 4;
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        fish = new ArrayList[N][N];
        smell = new int[N][N][101];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fish[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;

            if (i != M) {
                int d = Integer.parseInt(st.nextToken());
                fish[y][x].add(d);
                fishList.add(new int[]{y, x, d});
            }
            if (i == M) {
                sharkY = y;
                sharkX = x;
            }
        }

        // 여기까지 초기화 단계

        int count = 1;
        while (count <= S) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    smell[i][j][count] = smell[i][j][count-1];
                }
            }
            step1_readyCopy();
            step2_fishMove(count);
            step3_sharkMove(count);
            step4_removeSmell(count);
            step5_copyTemp();
            count++;
        }

//        step1_readyCopy();
//        step2_fishMove(1);
//        step3_sharkMove(1);
//        step4_removeSmell(1);
//        step5_copyTemp();
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                smell[i][j][2] = smell[i][j][1];
//            }
//        }
//
//        step1_readyCopy();
//        step2_fishMove(2);

        calculateSum();
        System.out.println(result);

//        for (ArrayList<Integer>[] a : fish) {
//            for (ArrayList<Integer> b : a) {
//                System.out.print(b + " ");
//            }
//            System.out.println();
//        }

//        while (!fishList.isEmpty()) {
//            System.out.println(Arrays.toString(fishList.poll()));
//        }

//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < N; j++) {
//                System.out.print(smell[i][j][1] + " ");
//            }
//            System.out.println();
//        }

    }

    private static void step1_readyCopy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (fish[i][j].size() != 0) {
                    for (int k = 0; k < fish[i][j].size(); k++) {
                        fishTemp.push(new int[]{i, j, fish[i][j].get(k)});
                    }
                }
            }
        }
    }

    private static void step2_fishMove(int curS) {
        int size = fishList.size();
        while (size > 0) {
            size--;
            int[] cur = fishList.poll();
            int curY = cur[0];
            int curX = cur[1];
            int dir = cur[2];
            if(fish[curY][curX].size() == 0) continue;
            fish[curY][curX].remove(Integer.valueOf(dir));
            int nextY;
            int nextX;
            for(int i = 0; i < 8; i++) {
                nextY = curY + dy8[dir];
                nextX = curX + dx8[dir];
                if (nextY < 0 || nextX < 0 || nextY >= N || nextX >= N || sharkY == nextY && sharkX == nextX || smell[nextY][nextX][curS] == 1) {
                    dir--;
                    if (dir == 0) {
                        dir = 8;
                    }
                } else {
//                    if (dir == 6) {
////                        System.out.println("cur : " + curY + "," + curX);
//                    }
                    fish[nextY][nextX].add(dir);
                    fishList.add(new int[]{nextY, nextX, dir});
                    break;
                }
            }
        }
    }

    private static void step3_sharkMove(int curS) {
        int maxCount = Integer.MIN_VALUE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    int nextY_1 = sharkY + dy4[i];
                    int nextX_1 = sharkX + dx4[i];
                    int nextY_2 = nextY_1 + dy4[j];
                    int nextX_2 = nextX_1 + dx4[j];
                    int nextY_3 = nextY_2 + dy4[k];
                    int nextX_3 = nextX_2 + dx4[k];
                    try {
                        int sum = fish[nextY_1][nextX_1].size() + fish[nextY_2][nextX_2].size();
                        if (nextY_1 != nextY_3 || nextX_1 != nextX_3) {
                            sum += fish[nextY_3][nextX_3].size();
                        }
                        if(sum >= maxCount) {
                            if(sum > maxCount) {
                                maxCount = sum;
                                sharkTemp.clear();
                            }
                            sb.append(i+1);
                            sb.append(j+1);
                            sb.append(k+1);
                            sharkTemp.add(Integer.valueOf(sb.toString()));
                            sb.delete(0, sb.length());
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {}
                }
            }
        }
//        System.out.println("maxCount = " + maxCount);
        Collections.sort(sharkTemp);
        Integer bestWay = sharkTemp.get(0); // 111  131
        String trans = Integer.toString(bestWay);
//        System.out.println(trans);
        int dir1 = Character.getNumericValue(trans.charAt(0));
        int dir2 = Character.getNumericValue(trans.charAt(1));
        int dir3 = Character.getNumericValue(trans.charAt(2));
        int nextY_1 = sharkY + dy4[dir1-1];
        int nextX_1 = sharkX + dx4[dir1-1];
        int nextY_2 = nextY_1 + dy4[dir2-1];
        int nextX_2 = nextX_1 + dx4[dir2-1];
        sharkY = nextY_2 + dy4[dir3-1];
        sharkX = nextX_2 + dx4[dir3-1];

        if(fish[nextY_1][nextX_1].size() > 0) {
            fish[nextY_1][nextX_1].clear();
            smell[nextY_1][nextX_1][curS] = 1;
            sharkEat.add(new int[]{nextY_1, nextX_1});
        }
        if(fish[nextY_2][nextX_2].size() > 0) {
            fish[nextY_2][nextX_2].clear();
            smell[nextY_2][nextX_2][curS] = 1;
            sharkEat.add(new int[]{nextY_2, nextX_2});
        }
        if(fish[sharkY][sharkX].size() > 0) {
            fish[sharkY][sharkX].clear();
            smell[sharkY][sharkX][curS] = 1;
            sharkEat.add(new int[]{sharkY, sharkX});
        }
        sharkTemp.clear();
    }

    private static void step4_removeSmell(int curS) {
        if(curS < 3) {
            return;
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (smell[i][j][curS - 2] == 1) {
                    smell[i][j][curS] = 0;
                }
            }
        }
    }

    private static void step5_copyTemp() {
        while (!fishTemp.isEmpty()) {
            int[] cur = fishTemp.pop();
            fish[cur[0]][cur[1]].add(cur[2]);
            fishList.add(new int[]{cur[0], cur[1], cur[2]});
        }
    }

    private static void calculateSum() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += fish[i][j].size();
            }
        }
    }

}
