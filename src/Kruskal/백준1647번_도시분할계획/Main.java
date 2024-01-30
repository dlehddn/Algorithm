package Kruskal.백준1647번_도시분할계획;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, sum, cnt;
    static int[] parent;
    static List<Integer> select;
    static PriorityQueue<Connection> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        select = new ArrayList<>();
        pq = new PriorityQueue<>();
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
        // 간선이 100 개
        // N - 1
        for (int i = 0; i < M; i++) {
            if(cnt == N - 1) break;

            Connection poll = pq.poll();
            if (find(poll.v1) != find(poll.v2)) {
                union(poll.v1, poll.v2);
                cnt++;
                sum += poll.cost;
                select.add(poll.cost);
            }
        }

        int maxCost = select.stream()
                .mapToInt(n -> n)
                .max()
                .orElse(-1);

        System.out.println(sum - maxCost);


    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }

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

    static class Connection implements Comparable<Connection>{
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
