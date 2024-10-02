package Greedy.백준1253번_좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static Map<Integer, List<Integer>> map; // 모든 숫자 저장, 겹칠 수 있으므로 각 숫자가 몇번째 인덱스인지 리스트로 관리

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            List<Integer> tmp = map.getOrDefault(arr[i], new ArrayList<>());
            tmp.add(i);
            map.put(arr[i], tmp);
        }
        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int sum = arr[i] + arr[j];
                List<Integer> list = map.get(sum);
                if (list == null) continue;
                for (int k = list.size() - 1; k >= 0; k--) {
                    Integer next = list.get(k);
                    if (next != i && next != j) {
                        result++;
                        list.remove(k);
                    }
                }
            }
        }
        System.out.println(result);

    }
}
