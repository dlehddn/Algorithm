package Kruskal.백준1774번_우주신과의교감;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, count;
    static double cost;
    static PriorityQueue<Connection> pq = new PriorityQueue<>(Comparator.comparingDouble(c -> c.cost));
    static int[] parent;
    static Coordinate[] coordinates;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        coordinates = new Coordinate[N + 1];

        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            coordinates[i] = new Coordinate(x, y);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (find(start) != find(end)) {
                union(start, end);
                count++;
            }
        }
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                double cost = Math.sqrt(Math.pow(coordinates[i].x - coordinates[j].x, 2) +
                        Math.pow(coordinates[i].y - coordinates[j].y, 2));
                pq.add(new Connection(i, j, cost));
            }
        }



        while (!pq.isEmpty()) {
            Connection poll = pq.poll();
            if (find(poll.start) != find(poll.end)) {
                union(poll.start, poll.end);
                count++;
                cost += poll.cost;
            }
            if (count == N - 1) {
                System.out.println(String.format("%.2f", cost));
                return;
            }
        }

    }

    static void union(int y, int x) {
        int parentY = find(y);
        int parentX = find(x);
        if (parentY < parentX) {
            parent[parentX] = parentY;
        } else {
            parent[parentY] = parentX;
        }
    }

    static int find(int a) {
        if (parent[a] == a) {
            return a;
        }
        return find(parent[a]);
    }

    static class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Connection {
        int start, end;
        double cost;

        public Connection(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
