package UnionFind.백준1717번_집합의표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (type == 0) {
                union(a, b);
            } else if (type == 1) {
                if (find(a) == find(b)) {
                    sb.append("YES" + "\n");
                } else {
                    sb.append("NO" + "\n");
                }
            }

        }
        System.out.println(sb);


    }

    static int find(int a) {
        if (parents[a] == a) return a;

        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b) {
        int pA = find(a);
        int pB = find(b);

        if (pA != pB) {
            if (pA < pB) {
                parents[pB] = pA;
            } else {
                parents[pA] = pB;
            }
        }

    }
}
