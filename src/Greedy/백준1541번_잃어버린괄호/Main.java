package Greedy.백준1541번_잃어버린괄호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();
        char prevOps = '+';
        int total = 0;
        int pSum = 0;

        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                sb.append(input.charAt(i));
                if (i != input.length() - 1) {
                    continue;
                }
            }
            int amount = Integer.parseInt(sb.toString());
            if (i == input.length() - 1 || input.charAt(i) == '-') {
                pSum += amount;
                if (prevOps == '+') {
                    total += pSum;
                } else {
                    total -= pSum;
                }
                pSum = 0;
                prevOps = '-';
            } else {
                pSum += amount;
            }
            sb = new StringBuilder();
        }
        System.out.println(total);
    }
}
