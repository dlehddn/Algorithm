package Samsung.마법사상어와비바라기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 1. 8칸의 움직임에 대한 dx, dy 만들기
 * 2. 현재 구름의 위치를 담는 스택 만들기
 * 3. 스택 비워내고 나머지 에서 물이 2이상인 애들 새롭게 담아야하니까 visited 만들기
 *
 * 만들어야 하는 메소드
 * 1. 구름 하나씩 꺼내서 비내리는 메소드 만들기
 * 2. 대각선 4방향에 물있는지 체크하고 더해주는 메소드 만들기
 * 3. 구름 새롭게 채우는 메소드 (어짜피 채우면 이동해야하니까 이걸 temp로 담아두고 4번 메소드에서 실제 이동 후 넣으면 될듯)
 * 4. 구름 이동하는 메소드
 */
public class Main {
    private static int N, M, result;
    private static boolean[][] visited;
    private static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1}; // 대각선 인덱스는 2, 4, 6, 8
    private static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    private static int[][] map;
    private static Stack<int[]> stack_temp = new Stack<>();
    private static Stack<int[]> stack_1 = new Stack<>();
    private static Stack<int[]> stack_2 = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = 0;

        visited = new boolean[N][N];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        stack_temp.push(new int[]{N-1, 0});
        stack_temp.push(new int[]{N-1, 1});
        stack_temp.push(new int[]{N-2, 0});
        stack_temp.push(new int[]{N-2, 1});

        while (M > 0) {
            M--;
            visited = new boolean[N][N];
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            moveCloud(dir, count);
            raining();
            copyWater();
            createCloud();
        }

        calculateSum();
        System.out.println(result);

//        for (int[] a : map) {
//            for (int b : a) {
//                System.out.print(b + " ");
//            }
//            System.out.println();
//        }


    }

    private static void raining() {
        while (!stack_1.isEmpty()) {
            int[] cur = stack_1.pop();
            map[cur[0]][cur[1]] += 1;
            visited[cur[0]][cur[1]] = true;   // 어디선가 초기화가 꼭 필요하다.
            stack_2.push(cur);
        }
    }

    private static void copyWater() {
        while (!stack_2.isEmpty()) {
            int count = 0;
            int[] cur = stack_2.pop();
            for (int i = 1; i <= 4; i++) {
                int nextY = cur[0] + dy[2 * i];
                int nextX = cur[1] + dx[2 * i];
                try {
                    if (map[nextY][nextX] > 0) {
                        count++;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {}
            }
            map[cur[0]][cur[1]] += count;
        }
    }

    private static void createCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] >= 2) {
                    stack_temp.push(new int[]{i, j});
                    map[i][j] -= 2;
                }
            }
        }
    }

    private static void moveCloud(int dir, int count) {
        while (!stack_temp.isEmpty()) {
            int[] cur = stack_temp.pop();
            int nextY = cur[0] + dy[dir] * count;
            int nextX = cur[1] + dx[dir] * count;

            if (nextY >= N) {
                nextY = nextY % N;
            }
            while (nextY < 0) {
                nextY += N;
            }
            if (nextX >= N) {
                nextX = nextX % N;
            }
            while (nextX < 0) {
                nextX += N;
            }
            stack_1.push(new int[]{nextY, nextX});
//            System.out.println(nextY + ", " + nextX);
        }
    }

    private static void calculateSum() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }
    }

}
