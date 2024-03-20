package Greedy.백준2138번_전구와스위치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N, click1, click2;
    static int[] init1, init2, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init1 = new int[N];
        init2 = new int[N];
        target = new int[N];

        String initString = br.readLine();
        String targetString = br.readLine();

        for (int i = 0; i < N; i++) {
            init1[i] = initString.charAt(i) - '0';
            init2[i] = initString.charAt(i) - '0';
            target[i] = targetString.charAt(i) - '0';
        }

        for (int i = 1; i < N; i++) {
            if (init1[i - 1] != target[i - 1]) {
                click1++;
                init1[i] = Math.abs(init1[i] - 1);
                init1[i - 1] = Math.abs(init1[i - 1] - 1);
                try {
                    init1[i + 1] = Math.abs(init1[i + 1] - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }

        init2[0] = Math.abs(init2[0] - 1);
        init2[1] = Math.abs(init2[1] - 1);
        click2++;

        for (int i = 1; i < N; i++) {
            if (init2[i - 1] != target[i - 1]) {
                click2++;
                init2[i] = Math.abs(init2[i] - 1);
                init2[i - 1] = Math.abs(init2[i - 1] - 1);
                try {
                    init2[i + 1] = Math.abs(init2[i + 1] - 1);
                } catch (ArrayIndexOutOfBoundsException e) {
                }
            }
        }

        boolean init1OK = init1[N - 1] == target[N - 1];
        boolean init2OK = init2[N - 1] == target[N - 1];

        if (init1OK && init2OK) {
            System.out.println(Math.min(click1, click2));
        } else if (init1OK && click1 == 0) {
            System.out.println(0);
        } else if (init1OK && !init2OK) {
            System.out.println(click1);
        } else if (init2OK && !init1OK) {
            System.out.println(click2);
        } else if (!init1OK && !init2OK) {
            System.out.println(-1);
        }
    }
}
