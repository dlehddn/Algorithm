package Greedy.백준12904번_A와B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        while (S.length() < T.length()) {
            if (T.charAt(T.length() - 1) == 'B') {
                T = T.substring(0, T.length() - 1);
                T = new StringBuilder(T).reverse().toString();
            } else {
                T = T.substring(0, T.length() - 1);
            }
            if (S.equals(T)) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}
