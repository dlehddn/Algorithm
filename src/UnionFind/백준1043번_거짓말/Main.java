package UnionFind.백준1043번_거짓말;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int N, M, result, trueFriendSize;
    static ArrayList<Integer>[] list;
    static int[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N + 1];
        list = new ArrayList[M];
        for (int i = 0; i < M; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i <= N; i++) {
            graph[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        trueFriendSize = Integer.parseInt(st.nextToken());

        for (int i = 0; i < trueFriendSize; i++) {
            graph[Integer.parseInt(st.nextToken())] = 0;
        }

        for (int i = 0; i < M; i++) {
            int prev = 0;
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            for (int j = 0; j < size; j++) {
                int n = Integer.parseInt(st.nextToken());
                list[i].add(n);
                if (j != 0) {
                    union(prev, n);
                }
                prev = n;
            }
        }

        for (int i = 0; i < M; i++) {
            boolean ok = true;
            for (Integer n : list[i]) {
                if (find(n) == 0) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                result += 1;
            }
        }

        System.out.println(result);
    }

    static int find(int x) {
        if (x == graph[x]) {
            return x;
        }

        return graph[x] = find(graph[x]);
    }

    static void union(int x, int y) {
        int a = find(x);
        int b = find(y);

        if (a < b) {
            graph[b] = a;
        } else {
            graph[a] = b;
        }
    }
}
