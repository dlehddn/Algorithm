package Greedy.백준19941번_햄버거분배;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static String line;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        line = br.readLine();
        visited = new boolean[N];
        int cnt = 0;

        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == 'H') continue;

            for (int j = Math.max(0, i - K); j <= i + K; j++) {
                if (j >= line.length()) break;
                if (j == i) continue;

                if (line.charAt(j) == 'H' && !visited[j]) {
                    visited[j] = true;
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);

    }
}
