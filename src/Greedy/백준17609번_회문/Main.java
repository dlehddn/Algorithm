package Greedy.백준17609번_회문;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String line = br.readLine();
            sb.append(solution(line, 0, line.length() - 1, 0));
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    static int solution(String line, int left, int right, int flag) {
        if (left > right) return flag;

        if (line.charAt(left) == line.charAt(right)) {
            return solution(line, left + 1, right - 1, flag);
        } else {
            if(flag == 1) return 2;
            int solution1 = solution(line, left + 1, right, flag + 1);
            int solution2 = solution(line, left, right - 1, flag + 1);
            if(solution1 != 2) return solution1;
            else return solution2;
        }
    }
}
