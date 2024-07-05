package SegmentTree;

public class SegmentTree {
    long[] tree;
    int treeSize;

    public SegmentTree(int N) {
        int h = (int) Math.ceil(Math.log(N) / Math.log(2));
        this.treeSize = (int) Math.pow(2, h + 1);
        tree = new long[treeSize];
    }

    long init(long[] arr, int start, int end, int curNode) {
        if (start == end) {
            return tree[curNode] = arr[start];
        }

        int mid = (start + end) / 2;
        return tree[curNode] = init(arr, start, mid, curNode * 2)
                + init(arr, mid + 1, end, curNode * 2 + 1);
    }

    long sum(long[] arr, int start, int end, int curNode, int left, int right) {
        if (left > end || right < start) return 0;

        if (left <= start && end <= right) return tree[curNode];

        int mid = (start + end) / 2;
        return sum(arr, start, mid, curNode * 2, left, right)
                + sum(arr, mid + 1, end, curNode * 2 + 1, left, right);
    }

    void update(long[] arr, int start, int end, int curNode, int arrIdx, long diff) {
        if (arrIdx < start || arrIdx > end) return;

        tree[curNode] += diff;
        if (start != end) {
            int mid = (start + end) / 2;
            update(arr, start, mid, curNode * 2, arrIdx, diff);
            update(arr, mid + 1, end, curNode * 2 + 1, arrIdx, diff);
        }
    }
}
