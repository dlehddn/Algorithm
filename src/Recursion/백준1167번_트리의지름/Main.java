package Recursion.백준1167번_트리의지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int V, result;
    static List<Edge>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        tree = new List[V + 1];
        visited = new boolean[V + 1];

        for (int i = 1; i < V + 1; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            while (true) {
                int e = Integer.parseInt(st.nextToken());
                if (e == -1) break;
                int cost = Integer.parseInt(st.nextToken());
                tree[s].add(new Edge(e, cost));
            }
        }

        recursion(1, 0);
        System.out.println(result);

    }

    static int recursion(int root, int cost) {
        visited[root] = true;
        if (tree[root].size() == 1 && root != 1) {
            return tree[root].get(0).cost;
        }

        List<Integer> results = new ArrayList<>();
        for (Edge edge : tree[root]) {
            if (!visited[edge.vertex]) {
                results.add(recursion(edge.vertex, edge.cost));
            }
        }
        results.sort(Comparator.reverseOrder());

        int tmp = results.get(0);
        if (results.size() > 1) {
            tmp += results.get(1);
        }
        if (tmp > result) {
            result = tmp;
        }
        return results.get(0) + cost;
    }

    static class Edge {
        int vertex, cost;

        public Edge(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
