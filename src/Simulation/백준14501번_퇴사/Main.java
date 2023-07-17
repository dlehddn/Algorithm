package Simulation.백준14501번_퇴사;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int maxMoney;
    private static int N;
    private static int[][] tasks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tasks = new int[N][2];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            tasks[i][0] = Integer.parseInt(st.nextToken());
            tasks[i][1] = Integer.parseInt(st.nextToken());
        }
        recursion(0,0);
        bw.write(Integer.toString(maxMoney));
        bw.flush();
        bw.close();
    }

    public static void recursion(int index, int money) {
        for(int i = index; i < N; i++) {
            System.out.println(i);
            int next_index = i + tasks[i][0];

            if (next_index < N) {
                int sum = money + tasks[i][1];
                recursion(next_index, sum);
            }
            else if (next_index == N) {
                int sum = money + tasks[i][1];
                if (sum > maxMoney) maxMoney = sum;
            }
            else {
                if (money > maxMoney) maxMoney = money;
            }
        }
    }
}

//4
//3 1
//1 100
//2 100
//1 1000