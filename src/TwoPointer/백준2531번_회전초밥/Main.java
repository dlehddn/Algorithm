package TwoPointer.백준2531번_회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, D, K, C, max;
    static int[] sushi;
    static Map<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        sushi = new int[N];
        map = new HashMap();

        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < K; i++) {
            map.put(sushi[i], map.getOrDefault(sushi[i], 0) + 1);
        }

        max = map.size();
        if (!map.containsKey(C)) {
            max++;
        }

        for (int i = 1; i < N; i++) {
            int cnt = map.get(sushi[i - 1]);
            if (cnt > 1) {
                map.put(sushi[i - 1], cnt - 1);
            } else if (cnt == 1) {
                map.remove(sushi[i - 1]);
            }
            int next = i + K - 1;
            if (next >= N) {
                next = (i + K - 1) % N;
            }
            map.put(sushi[next], map.getOrDefault(sushi[next], 0) + 1);
            max = Math.max(max, map.containsKey(C) ? map.size() : map.size() + 1);
        }
        System.out.println(max);
    }
}
