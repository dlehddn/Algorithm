package Recursion.백준1967번_트리의지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, result;
    static List<Node>[] graph;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to, cost));
        }

        dfs(new Node(1, 0));
        System.out.println(result);
    }

    static int dfs(Node node) {
        if (graph[node.vertex].size() == 0) {
            return node.cost;
        }

        List<Integer> list = new ArrayList<>();
        for (Node n : graph[node.vertex]) {
            list.add(dfs(n));
        }
        list.sort(Comparator.reverseOrder());
        int sum = list.get(0);
        if (list.size() > 1) {
            sum += list.get(1);
        }
        if (result < sum) result = sum;
        return list.get(0) + node.cost;
    }

    static class Node {
        int vertex, cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }
    }
}
