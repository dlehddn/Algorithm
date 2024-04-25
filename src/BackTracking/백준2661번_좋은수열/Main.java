package BackTracking.백준2661번_좋은수열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sb.append(1);
        backTrack(0, 1, sb);

    }

    static boolean backTrack(int idx, int select, StringBuilder sb) {

        for (int k = 1; k <= idx / 2 + idx % 2; k++) {
            if (sb.substring(idx + 1 - k).equals(sb.substring(idx + 1 - 2 * k, idx + 1 - k))) {
                return false;
            }
        }

        if (sb.length() == N) {
            System.out.println(sb);
            return true;
        }

        boolean done = false;
        for (int i = 1; i <= 3; i++) {
            sb.append(i);
            if (backTrack(idx + 1, i, sb)) {
                done = true;
                break;
            }
            sb.deleteCharAt(sb.length() - 1);
        }

        if (!done) {
            return false;
        }
        return true;
    }
}
