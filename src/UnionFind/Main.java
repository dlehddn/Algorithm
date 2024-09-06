package UnionFind;

public class Main {

    static int[] parent;

    public static void main(String[] args) {
        parent = new int[10];

        for (int i = 0; i < 10; i++) {
            parent[i] = i;
        }
    }

    static int find(int a) {
        if (parent[a] == a) return a;

        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if (pA < pB) {
            parent[pB] = pA;
        } else {
            parent[pA] = pB;
        }
    }
}
