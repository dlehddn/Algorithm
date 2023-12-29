package UnionFind.백준1976번_여행가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Year;
import java.util.*;

public class Main {

    static int[][] graph;
    static int[] schedule, parent;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        schedule = new int[M];
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    graph[i][j] = 1;
                }
            }
        }

        String[] tmp = br.readLine().split(" ");
        for (int i = 0; i < M; i++) {
            schedule[i] = Integer.parseInt(tmp[i]);
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        for (int i = 0; i < schedule.length - 1; i++) {
            if (find(schedule[i]) != find(schedule[i + 1])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    
    static int find(int x) {
        if(x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if (a != b) {
            if(a < b) parent[b] = a;
            else parent[a] = b;
        }
    }
}
