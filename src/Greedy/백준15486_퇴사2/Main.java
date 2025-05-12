package Greedy.백준15486_퇴사2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Task[] tasks;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tasks = new Task[N];
        visited = new int[N];
        Arrays.fill(visited, -1);
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            tasks[i] = new Task(cost, pay);
        }

        int max = 0;

    }

    static class Task {
        int cost, pay;

        public Task(int cost, int pay) {
            this.cost = cost;
            this.pay = pay;
        }
    }
}
