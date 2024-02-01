package Kruskal.백준4386번_별자리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, cnt;
    static int[] parent;
    static Star[] stars;
    static PriorityQueue<Connection> pq;
    static double min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        stars = new Star[N];
        pq = new PriorityQueue<>((con1, con2) -> Double.compare(con1.cost, con2.cost));
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            double y = Double.parseDouble(st.nextToken());
            double x = Double.parseDouble(st.nextToken());
            stars[i] = new Star(y, x);
        }

        for (int i = 0; i < N; i++) {
            Star main = stars[i];
            for (int j = 0; j < N; j++) {
                if(i == j) continue;
                Star sub = stars[j];
                pq.add(new Connection(i, j, calDistance(main, sub)));
            }
        }

        while (true) {
            if(cnt == N - 1) break;

            Connection poll = pq.poll();
            if (find(poll.v1) != find(poll.v2)) {
                cnt++;
                union(poll.v1, poll.v2);
                min += poll.cost;
            }
        }

        System.out.println(min);
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int y, int x) {
        int a = find(y);
        int b = find(x);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static double calDistance(Star v1, Star v2) {
        return Math.round(Math.sqrt(Math.pow(Math.abs(v1.x-v2.x), 2) + Math.pow(Math.abs(v1.y-v2.y), 2)) * 100) / 100.0;
    }

    static class Star {
        double y, x;

        public Star(double y, double x) {
            this.y = y;
            this.x = x;
        }
    }

    static class Connection{
        int v1, v2;
        double cost;

        public Connection(int v1, int v2, double cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }
    }
}
