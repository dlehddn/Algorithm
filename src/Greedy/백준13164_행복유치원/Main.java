package Greedy.백준13164_행복유치원;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        List<Diff> diffs = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            diffs.add(new Diff(arr[i + 1] - arr[i], i, i + 1));
        }
        diffs.sort((d1, d2) -> d2.diff - d1.diff);

        List<Diff> cuts = new ArrayList<>();
        for (int i = 0; i < K - 1; i++) {
            cuts.add(diffs.get(i));
        }

        cuts.sort(Comparator.comparingInt(d -> d.left));
        long cost = 0;
        int start = 0;
        for (Diff cut : cuts) {
            cost += arr[cut.left] - arr[start];
            start = cut.right;
        }
        cost += arr[N - 1] - arr[start];

        System.out.println(cost);
    }

    static class Diff {
        int diff, left, right;

        public Diff(int diff, int left, int right) {
            this.diff = diff;
            this.left = left;
            this.right = right;
        }
    }
}
