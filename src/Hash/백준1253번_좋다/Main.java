package Hash.백준1253번_좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;
    static Map<Integer, List<Integer>> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
        }
        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int target = arr[i] + arr[j];
                List<Integer> list = map.getOrDefault(target, new ArrayList<>());
                if (list.size() != 0) {
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        int next = iterator.next();
                        if (i != next && j != next) {
                            cnt++;
                            iterator.remove();
                        }
                    }
                }
            }
        }
        System.out.println(cnt);
    }

}
