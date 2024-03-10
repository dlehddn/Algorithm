package BruteForce.백준1027번_고층건물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] arr;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        max = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            double maxG = Integer.MIN_VALUE;
            int tmp = 0;
            for (int j = i + 1; j < N; j++) {
                double g = (double) (arr[j] - arr[i]) / (j - i);
                if (g > maxG) {
                    tmp++;
                    maxG = g;
                }
            }

            maxG = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                double g = (double) (arr[i] - arr[j]) / (i - j);
                if (g < maxG) {
                    tmp++;
                    maxG = g;
                }
            }

            if (tmp > max) {
                max = tmp;
            }
        }
        System.out.println(max);
    }
}
