package Simulation.백준14891번_톱니바퀴;


import java.io.*;
import java.util.*;

public class Main {

    private static List<Integer>[] node = new ArrayList[5];
    private static boolean[] edge = new boolean[8];
    private static int[][] dial = new int[4][8];
    private static boolean[] visited;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        for(int i = 0; i < 5; i++) {
            node[i] = new ArrayList<>();
        }

        node[1].add(2);
        node[2].add(1);
        node[2].add(3);
        node[3].add(2);
        node[3].add(4);
        node[4].add(3);

        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                char temp = line.charAt(j);
                dial[i][j] = temp - '0';
            }
        }

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int dialNum = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            makeEdge();
            visited = new boolean[5];
            dfs(dialNum, direction);

        }
        calculateSum();

        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }

    private static void makeEdge() {
        if(dial[0][2] == dial[1][6]) edge[3] = false;
        else edge[3] = true;

        if(dial[1][2] == dial[2][6]) edge[5] = false;
        else edge[5] = true;

        if(dial[2][2] == dial[3][6]) edge[7] = false;
        else edge[7] = true;
    }

    private static void moveDial(int dialNumber, int direction) {
        if(direction == 1) { // 시계방향
            int lastIndexValue = dial[dialNumber-1][7];
            for (int i = 7; i > 0; i--) {
                dial[dialNumber-1][i] = dial[dialNumber-1][i - 1];
            }
            dial[dialNumber-1][0] = lastIndexValue;
        }

        if(direction == -1) { // 반시계방향
            int firstIndexValue = dial[dialNumber-1][0];
            for (int i = 0; i < 7; i++) {
                dial[dialNumber-1][i] = dial[dialNumber-1][i + 1];
            }
            dial[dialNumber-1][7] = firstIndexValue;
        }
    }

    private static void dfs(int dialNumber, int direction) {
        visited[dialNumber] = true;
        moveDial(dialNumber, direction);

        for (int a : node[dialNumber]) {
            if (!visited[a] && edge[dialNumber + a]) {
                dfs(a, -direction);
            }
        }
    }

    /**
     * bfs 외 않되?;;;;;;
     */
//    private static void bfs(int dialNumber, int direction) {
//        Queue<Integer> q = new LinkedList<>();
//        q.add(dialNumber);
//        visited[dialNumber] = true;
//        moveDial(dialNumber, direction);
//
//        while(!q.isEmpty()) {
//            dialNumber = q.poll();
//            direction *= -1;
//            for(int a : node[dialNumber]) {
//                if(!visited[a] && edge[dialNumber + a]) {
//                    visited[a] = true;
//                    q.add(a);
//                    moveDial(a, direction);
//                }
//            }
//        }
//    }

    private static void calculateSum() {
        if(dial[0][0] == 1) count += 1;
        if(dial[1][0] == 1) count += 2;
        if(dial[2][0] == 1) count += 4;
        if(dial[3][0] == 1) count += 8;
    }
}

//11001100
//00001101
//11000100
//11010001
//10
//3 1
//1 -1
//3 1
//2 -1
//3 1
//2 -1
//4 1
//1 1
//3 1
//2 -1
// 답 : 2


//11111110
//00000000
//11111111
//11111111
//1
//2 -1
//
//답: 12

