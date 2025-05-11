package Greedy.백준20365번_블로그2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String line = br.readLine();
        int blueCnt = 0;
        int redCnt = 0;

        char prev = 'N';

        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c != prev) {
                if (c == 'B') {
                    blueCnt++;
                    prev = 'B';
                } else {
                    redCnt++;
                    prev = 'R';
                }
            }
        }

        System.out.println(Math.min(redCnt, blueCnt) + 1);
    }
}
