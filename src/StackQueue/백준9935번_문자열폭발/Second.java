package StackQueue.백준9935번_문자열폭발;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Second {
    static String input, bombStr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        bombStr = br.readLine();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));
            if (sb.length() >= bombStr.length() && sb.charAt(sb.length() - 1) == bombStr.charAt(bombStr.length() - 1)) {
                String compared = sb.substring(sb.length() - bombStr.length());
                if (compared.equals(bombStr)) {
                    sb.delete(sb.length() - bombStr.length(), sb.length());
                }
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}
