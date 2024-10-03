package Greedy.백준13144번_ListOfUniqueNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        visited = new boolean[100001];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        long cnt = 0;
        visited[arr[start]] = true;

        while (start < N) {
            while (end < N - 1 && !visited[arr[end + 1]]) {
                end++;
                visited[arr[end]] = true;
            }
            cnt += end - start + 1;
            visited[arr[start]] = false;
            start++;
//            System.out.println("(end, start) = " + end + ", " + start);
        }

        System.out.println(cnt);
    }
}
