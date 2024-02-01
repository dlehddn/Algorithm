package Kruskal.백준6479번_전력난;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, save, cnt;
    static int[] parent;
    static PriorityQueue<Connection> pq;
    static List<Connection> tmpList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) return;
            save = 0;
            cnt = 0;
            parent = new int[N + 1];
            pq = new PriorityQueue<>();
            tmpList = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                pq.add(new Connection(v1, v2, cost));
            }

            while (true) {
                if (cnt == N - 1) break;
                Connection poll = pq.poll();
                if (find(poll.v1) != find(poll.v2)) {
                    cnt++;
                    union(poll.v1, poll.v2);
                } else {
                    tmpList.add(poll);
                }
            }

            while (!pq.isEmpty()) {
                Connection poll = pq.poll();
                save += poll.cost;
            }

            for (Connection con : tmpList) {
                save += con.cost;
            }

            System.out.println(save);
        }
    }

    static int find(int x) {
        if (x == parent[x]) return x;

        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    static class Connection implements Comparable<Connection> {
        int v1, v2, cost;

        public Connection(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Connection o) {
            return this.cost - o.cost;
        }
    }
}
