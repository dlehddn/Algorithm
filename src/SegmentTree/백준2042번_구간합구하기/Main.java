package SegmentTree.백준2042번_구간합구하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[] tree, arr;
    static int N, M, K, h, treeSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        h = (int) Math.ceil(Math.log(N) / Math.log(2));
        treeSize = (int) Math.pow(2, h + 1);
        arr = new long[N + 1];
        tree = new long[treeSize];
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                long diff = c - arr[b];
                arr[b] = c;
                update(1, N, 1, b, diff);
            } else {
                sb.append(sum(1, N, 1, b, (int) c) + "\n");
            }
        }
        System.out.println(sb);


    }

    static long init(int start, int end, int curNode) {
        if (start == end) {
            return tree[curNode] = arr[start];
        }

        return tree[curNode] = init(start, (start + end) / 2, 2 * curNode)
                + init((start + end) / 2 + 1, end, 2 * curNode + 1);
    }

    static void update(int start, int end, int curNode, int arrIdx, long diff) {
        if (start > arrIdx || end < arrIdx) return;

        tree[curNode] += diff;
        if (start == end) return; // 이 놈의 위치가 중요
        update(start, (start + end) / 2, 2 * curNode, arrIdx, diff);
        update((start + end) / 2 + 1, end, 2 * curNode + 1, arrIdx, diff);
    }

    static long sum(int start, int end, int curNode, int left, int right) {
        if (start > right || end < left) return 0;

        if (left <= start && end <= right) return tree[curNode];

        return sum(start, (start + end) / 2, curNode * 2, left, right)
                + sum((start + end) / 2 + 1, end, curNode * 2 + 1, left, right);
    }







}
