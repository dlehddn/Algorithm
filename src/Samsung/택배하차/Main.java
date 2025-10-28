package Samsung.택배하차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static List<Integer>[] stacks;
    static int[] indegree;
    static Set<Integer> outs = new HashSet<>();
    static List<Box> boxes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        map = new int[N][N];
        stacks = new List[M + 1];
        for (int i = 1; i <= M; i++) {
            stacks[i] = new ArrayList<>();
        }
        indegree = new int[101];
        Arrays.fill(indegree, Integer.MAX_VALUE);

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int startC = Integer.parseInt(st.nextToken()) - 1;
            drop(startC, 0, h, w, num);
        }




    }

    static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void drop(int startColumn, int startRow, int h, int w, int number) {
        int targetRow = 0;
        loop:
        for (int i = startRow + h; i < N; i++) {
            for (int j = startColumn; j < startColumn + w; j++) {
                if (map[i][j] != 0) break loop;
            }
            targetRow = i;
        }

        if (targetRow == N - 1) {
            indegree[number] = 1;
        } else {
            Set<Integer> set = new HashSet<>();
            for (int i = startColumn; i < startColumn + w; i++) {
                if (map[targetRow + 1][i] != 0) {
                    set.add(map[targetRow + 1][i]);
                }
            }
            indegree[number] = set.size();
            for (Integer i : set) {
                stacks[i].add(number);
            }
        }

        for (int i = targetRow; i > targetRow - h; i--) {
            for (int j = startColumn; j < startColumn + w; j++) {
                map[i][j] = number;
            }
        }
    }

    static class Box {
        int num, startY, startX, h, w;

        public Box(int num, int startY, int startX, int h, int w) {
            this.num = num;
            this.startY = startY;
            this.startX = startX;
            this.h = h;
            this.w = w;
        }
    }
}
