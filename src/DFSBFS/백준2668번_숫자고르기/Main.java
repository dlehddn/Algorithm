package DFSBFS.백준2668번_숫자고르기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static boolean[] visited;
    static int[] arr;
    static List<Integer> result;
    static int N;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/Greedy/백준2668번/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        arr = new int[N + 1];
        result = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            find(i, i, new ArrayList<>());
        }

        List<Integer> collect = result.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());


        System.out.println(collect.size());
        for (Integer integer : collect) {
            System.out.println(integer);
        }


    }

    static void find(int start, int cur, List<Integer> list) {
        if(visited[cur]) return;
        visited[cur] = true;

        list.add(cur);
        if (arr[cur] == start) {
            result.addAll(list);
        }
        find(start, arr[cur], list);
    }
}
