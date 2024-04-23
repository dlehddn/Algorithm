package Greedy.백준1543번_문서검색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static String document, target;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        document = br.readLine();
        target = br.readLine();

        while (document.contains(target)) {
            answer++;
            int idx = document.indexOf(target);
            document = document.substring(idx + target.length());
        }

        System.out.println(answer);

    }
}
