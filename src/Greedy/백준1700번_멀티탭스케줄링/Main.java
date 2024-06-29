package Greedy.백준1700번_멀티탭스케줄링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K, min;
    static int[] arr;
    static List<Integer> list;

    /**
     * 3 14
     * 1 4 3 2 5 4 3 2 5 3 4 2 3 4
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        arr = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N && i < K; i++) {
            if (!list.contains(arr[i])) {
                list.add(arr[i]);
            }
        }
        loop:
        for (int i = N; i < K; i++) {
            if (list.size() < N && !list.contains(arr[i])) {
                list.add(arr[i]);
            } else if (list.size() == N && !list.contains(arr[i])) {
                for (Integer n : list) {
                    boolean flag = false;
                    for (int j = K - 1; j > i; j--) {
                        if (arr[j] == n) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        list.remove(n);
                        list.add(arr[i]);
                        min++;
                        continue loop;
                    }
                }

                List<Integer> tmp = new ArrayList<>();
                for (int j = i + 1; j < K; j++) {
                    if (list.contains(arr[j]) && !tmp.contains(arr[j])) {
                        tmp.add(arr[j]);
                    }
                }
                list.remove(tmp.get(N - 1));
                min++;
                list.add(arr[i]);
            }
        }
        System.out.println(min);
    }

}
