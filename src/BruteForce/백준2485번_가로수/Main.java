package BruteForce.백준2485번_가로수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    static int N, min;
    static int[] exist;
    static PriorityQueue<Integer> distances = new PriorityQueue<>(Comparator.reverseOrder());

    /**
     * 시간 복잡도 계산을 잘못해 설계를 잘못함.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        exist = new int[N];
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(br.readLine());
            exist[i] = tmp;

            if (i > 0) {
                if (min > tmp - exist[i - 1]) {
                    min = tmp - exist[i - 1];
                }
            }

        }

        for (int i = 0; i < N; i++) {
            if (i == 0) continue;
            distances.add(exist[i] - exist[i - 1]);
        }


        while (distances.size() > 1) {
            int big = distances.poll();
            int small = distances.poll();

            distances.add(solution(big, small));
        }

        int range = distances.poll();
        System.out.println((exist[exist.length - 1] - exist[0]) / range + 1 - exist.length);
    }

    static int solution(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}
