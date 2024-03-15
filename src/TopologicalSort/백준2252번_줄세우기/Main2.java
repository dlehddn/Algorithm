package TopologicalSort.백준2252번_줄세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2 {
    /**
     * 실패.
     */

    static int N, M;
    static int[] before, after;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        before = new int[N + 1];
        after = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            before[i] = i;
            after[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            int seatF = before[front];
            int seatB = before[back];

            if (seatF > seatB) {
                before[front] = seatB;
                before[back] = seatF;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            System.out.print(before[i] + " ");
        }
    }
}
