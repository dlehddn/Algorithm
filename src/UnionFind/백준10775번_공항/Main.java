package UnionFind.백준10775번_공항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] parent;
    static int G, P;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());
        parent = new int[G + 1];

        for (int i = 0; i <= G; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= P; i++) {
            int airplane = Integer.parseInt(br.readLine());
            int target = find(airplane);
            if (target == 0) {
                System.out.println(i - 1);
                return;
            }
            union(target, target - 1);
        }
        System.out.println(P);
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
