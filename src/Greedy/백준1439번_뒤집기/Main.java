package Greedy.백준1439번_뒤집기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String line;
    static int zero, one;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        line = br.readLine();

        char previous = line.charAt(0);
        for (int i = 1; i < line.length(); i++) {
            if (line.charAt(i) != previous) {
                if (previous == '0') {
                    zero++;
                    previous = '1';
                } else {
                    one++;
                    previous = '0';
                }
            }
            if (i == line.length() - 1) {
                if (line.charAt(i) == '0') {
                    zero++;
                } else {
                    one++;
                }
            }
        }
        System.out.println(Math.min(zero, one));

    }

}
