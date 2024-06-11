package Comb_Per.백준15663번_N과M9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer> list;
    static List<String> result;
    static int[] choice;
    static boolean[] visited;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        choice = new int[M];
        visited = new boolean[N];
        result = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.sort(Comparator.naturalOrder());
        per(0);
        for (String s : result) {
            System.out.println(s);
        }

    }

    static void per(int cnt) {
        if (cnt == M) {
            StringBuilder sb = new StringBuilder();
            for (int i : choice) {
                sb.append(i + " ");
            }
//            for (String s : result) {
//                if (s.equals(sb.toString())) return;
//            }
            result.add(sb.toString());
            return;
        }

        int prev = 0;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && prev != list.get(i)) {
                visited[i] = true;
                choice[cnt] = list.get(i);
                prev = list.get(i);
                per(cnt + 1);
                visited[i] = false;
            }
        }
    }
}
