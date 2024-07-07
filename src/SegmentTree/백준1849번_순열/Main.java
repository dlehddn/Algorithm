package SegmentTree.백준1849번_순열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] infos, tree, result;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        infos = new int[N + 1];
        result = new int[N + 1];

        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        int treeSize = (int) Math.pow(2, h + 1);
        tree = new int[treeSize];

        for (int i = 1; i <= N; i++) {
            infos[i] = Integer.parseInt(br.readLine());
        }
        init(1, N, 1);

        for (int i = 1; i <= N; i++) {
            int idx = query(1, N, 1, infos[i]);
            result[idx] = i;
            update(1, N, 1, idx);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            sb.append(result[i] + "\n");
        }
        System.out.println(sb);

    }

    static int init(int start, int end, int node) {
        if (start == end) {
            if (start == 1) return tree[node] = 0;
            return tree[node] = 1;
        }

        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    static int query(int start, int end, int node, int cnt) {
        if (start == end) {
            return start;
        }

        if (tree[node * 2] < cnt) {
            return query((start + end) / 2 + 1, end, node * 2 + 1, cnt - tree[node * 2]);
        } else {
            return query(start, (start + end) / 2, node * 2, cnt);
        }
    }

    static void update(int start, int end, int node, int arrIdx) {
        if (arrIdx < start || arrIdx > end) return;

        if (start == end) tree[node] = 0;
        else {
            tree[node]--;
            int mid = (start + end) / 2;
            update(start, mid, node * 2, arrIdx);
            update(mid + 1, end, node * 2 + 1, arrIdx);
        }
    }


}
