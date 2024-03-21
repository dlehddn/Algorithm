package Comb_Per.백준6603번_로또;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int K;
    static int[] lotto;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            if(K == 0) break;

            lotto = new int[K];
            for (int i = 0; i < K; i++) {
                lotto[i] = Integer.parseInt(st.nextToken());
            }

            comb(0, 0, new int[6]);
            sb.append("\n");
        }

        System.out.println(sb);


    }

    static void comb(int start, int cnt, int[] picks) {
        if (cnt == 6) {
            for (int pick : picks) {
                sb.append(pick + " ");
            }
            sb.append("\n");
            return;
        }


        for (int i = start; i < lotto.length; i++) {
            picks[cnt] = lotto[i];
            comb(i + 1, cnt + 1, picks);
        }
    }
}
