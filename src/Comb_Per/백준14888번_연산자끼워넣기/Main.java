package Comb_Per.백준14888번_연산자끼워넣기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] numbers;
    static List<Character> operators;
    static int max, min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        operators = new ArrayList<>();
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int plusCnt = Integer.parseInt(st.nextToken());
        int minusCnt = Integer.parseInt(st.nextToken());
        int dotCnt = Integer.parseInt(st.nextToken());
        int divisionCnt = Integer.parseInt(st.nextToken());
        while (plusCnt-- > 0) {
            operators.add('+');
        }
        while (minusCnt-- > 0) {
            operators.add('-');
        }
        while (dotCnt-- > 0) {
            operators.add('*');
        }
        while (divisionCnt-- > 0) {
            operators.add('%');
        }
        per(0, new StringBuilder(), new boolean[N - 1]);
        System.out.println(max);
        System.out.println(min);
    }

    static int operate(int left, int right, char type) {
        if (type == '+') {
            return left + right;
        }
        if (type == '-') {
            return left - right;
        }
        if (type == '*') {
            return left * right;
        }
        return left / right;
    }

    static void calculate(StringBuilder sb) {
        int tmp = numbers[0];
        int nextIdx = 1;
        while (nextIdx < N) {
            tmp = operate(tmp, numbers[nextIdx], sb.charAt(nextIdx - 1));
            nextIdx++;
        }
        max = Math.max(max, tmp);
        min = Math.min(min, tmp);
    }

    static void per(int cnt, StringBuilder sb, boolean[] visited) {
        if (cnt == N - 1) {
            calculate(sb);
            return;
        }

        for (int i = 0; i < N - 1; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sb.append(operators.get(i));
                per(cnt + 1, sb, visited);
                visited[i] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}
