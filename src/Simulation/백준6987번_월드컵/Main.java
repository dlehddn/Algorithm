package Simulation.백준6987번_월드컵;

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int[][] playResult;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            boolean sumIsFive = true;
            st = new StringTokenizer(br.readLine());
            playResult = new int[6][3];
            for(int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    playResult[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            for(int j = 0; j < 6; j++) {
                if(playResult[j][0] + playResult[j][1] + playResult[j][2] != 5) sumIsFive = false;
            }

            if(isOk_first(playResult) && sumIsFive) {

                sb.append(1 + " ");
            }
            else {
                sb.append(0 + " ");
            }
        }

        sb.deleteCharAt(7);
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();

    }

    private static boolean isOk_first(int[][] playResult) {
        for(int i = 0; i < 6; i++) {
            int temp = playResult[i][0];
            int tempDraw = playResult[i][1];
            playResult[i][0] = 0;
            playResult[i][1] = 0;
            HashSet<Integer> setDraw = new HashSet<>();

            for (int j = 0; j < tempDraw; j++) {
                int maxNum = Integer.MIN_VALUE;
                int maxIndex = 0;
                for(int k = 0; k < 6; k++) {
                    if(k != 5 && k == i) continue;
                    if(setDraw.contains(k)) continue;
                    int currentNum = playResult[k][1];
                    if (currentNum > maxNum) {
                        maxNum = currentNum;
                        maxIndex = k;
                    }
                }
                setDraw.add(maxIndex);
                if(maxNum > 0) playResult[maxIndex][1] -= 1;
            }

            // 무승부 처리한 애들 담아두기
            HashSet<Integer> setLose = new HashSet<>();
            for (int j = 0; j < temp; j++) {
                int maxNum = Integer.MIN_VALUE;
                int maxIndex = 0;
                for(int k = 0; k < 6; k++) {
                    if(k != 5 && k == i) continue;
                    if(setLose.contains(k) || setDraw.contains(k)) continue;
                    int currentNum = playResult[k][2];
                    if (currentNum > maxNum) {
                        maxNum = currentNum;
                        maxIndex = k;
                    }
                }
                setLose.add(maxIndex);
                if(maxNum > 0) playResult[maxIndex][2] -= 1;
            }
        }
        for (int i = 0; i < 6; i++) {
            if(playResult[i][0] != 0 || playResult[i][2] != 0) return false;
        }
        return true;
    }
}

//5 0 0 5 0 0 5 0 0 0 0 5 0 0 5 0 0 5
//5 0 0 4 0 1 4 0 1 1 0 4 1 0 4 0 0 5
//0 5 0 5 0 0 0 0 5 0 5 0 0 5 0 0 5 0
//0 5 0 0 5 0 3 0 2 2 0 3 4 0 1 1 0 4


//3 1 1 1 0 4 1 1 3 3 0 2 3 0 2 3 0 2
//3 1 1 1 0 4 1 1 3 3 0 2 3 0 2 3 0 2
//3 1 1 1 0 4 1 1 3 3 0 2 3 0 2 3 0 2
//3 1 1 1 0 4 1 1 3 3 0 2 3 0 2 3 0 2

