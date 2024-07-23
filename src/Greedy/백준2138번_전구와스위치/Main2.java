package Greedy.백준2138번_전구와스위치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

    static int N, result;
    static int[] start1, end1, start2, end2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;

        start1 = new int[N];
        end1 = new int[N];
        start2 = new int[N];
        end2 = new int[N];

        String startS = br.readLine();
        String endS = br.readLine();

        for (int i = 0; i < N; i++) {
            start1[i] = startS.charAt(i) - '0';
            start2[i] = startS.charAt(i) - '0';
            end1[i] = endS.charAt(i) - '0';
            end2[i] = endS.charAt(i) - '0';
        }

        solution(start1, end1, 1);
        solution(start2, end2, 0);

        System.out.println(result != Integer.MAX_VALUE ? result : -1);
    }

    static void solution(int[] start, int[] end, int startIdx) {
        int cnt = 0;
        if (startIdx == 0) {
            cnt++;
            click(start, 0);
        }
        for (int i = 1; i < N; i++) {
            if (start[i - 1] != end[i - 1]) {
                cnt++;
                click(start, i);
            }
        }
        if (checkSame(start, end)) {
            result = Math.min(result, cnt);
        }
    }

    static boolean checkSame(int[] start, int[] end) {
        for (int i = 0; i < N; i++) {
            if (start[i] != end[i]) return false;
        }
        return true;
    }

    static void click(int[] start, int idx) {
        if (idx == 0) {
            start[0] = (start[0] + 1) % 2;
            start[1] = (start[1] + 1) % 2;
        } else if (idx == N - 1) {
            start[N - 2] = (start[N - 2] + 1) % 2;
            start[N - 1] = (start[N - 1] + 1) % 2;
        } else {
            start[idx - 1] = (start[idx - 1] + 1) % 2;
            start[idx] = (start[idx] + 1) % 2;
            start[idx + 1] = (start[idx + 1] + 1) % 2;
        }
    }


}
