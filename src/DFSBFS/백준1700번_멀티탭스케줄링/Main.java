package DFSBFS.백준1700번_멀티탭스케줄링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    /**
     * 시간 복잡도 계산 실수, 100 * 100이어서 완전탐색 가능한줄 알았는데
     * 100^100이네.
     */

    static int N, K, min;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        min = Integer.MAX_VALUE;
        arr = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(new ArrayList<>(), 0, 0);
        System.out.println(min);
    }

    static void dfs(List<Integer> usage, int idx, int remove) {
        if (idx == K) {
            if (remove < min) {
                min = remove;
            }
            return;
        }
        if (usage.size() < N) {
            usage.add(arr[idx]);
            dfs(usage, idx + 1, remove);
        } else if (usage.size() == N && usage.contains(arr[idx])) {
            dfs(usage, idx + 1, remove);
        } else {
            for (int i = 0; i < N; i++) {
                Integer num = usage.get(i);
                usage.set(i, arr[idx]);
                dfs(usage, idx + 1, remove + 1);
                usage.set(i, num);
            }
        }
    }
}
