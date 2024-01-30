package Kruskal.백준2887번_행성터널;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, sum, cnt;
    static PriorityQueue<Connection> pq;
    static int[] parent;
    static Planet[] planets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        parent = new int[N];
        planets = new Planet[N];
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(x, y, z, i);
        }

        Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.x, p2.x));
        for (int i = 1; i < N; i++) {
            pq.add(new Connection(planets[i].num, planets[i - 1].num,
                    Math.abs(planets[i].x - planets[i - 1].x)));
        }
        Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.y, p2.y));
        for (int i = 1; i < N; i++) {
            pq.add(new Connection(planets[i].num, planets[i - 1].num,
                    Math.abs(planets[i].y - planets[i - 1].y)));
        }
        Arrays.sort(planets, (p1, p2) -> Integer.compare(p1.z, p2.z));
        for (int i = 1; i < N; i++) {
            pq.add(new Connection(planets[i].num, planets[i - 1].num,
                    Math.abs(planets[i].z - planets[i - 1].z)));
        }

        for (int i = 0; i < 3 * N; i++) {
            if (cnt == N - 1) {
                break;
            }

            Connection poll = pq.poll();
            if (find(poll.v1) != find(poll.v2)) {
                union(poll.v1, poll.v2);
                cnt++;
                sum += poll.cost;
            }
        }

        System.out.println(sum);

    }

    static int find(int x) {
        if(x == parent[x]) return x;

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

    static class Planet {
        int x, y, z, num;

        public Planet(int x, int y, int z, int num) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.num = num;
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
