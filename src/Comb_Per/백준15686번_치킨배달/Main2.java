package Comb_Per.백준15686번_치킨배달;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main2 {

    static int N, M, min;
    static List<Pair> houses;
    static List<Pair> chickens;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        houses = new ArrayList<>();
        chickens = new ArrayList<>();
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    houses.add(new Pair(i, j));
                } else if (n == 2) {
                    chickens.add(new Pair(i, j));
                }
            }
        }
        comb(0, 0, new Pair[M]);
        System.out.println(min);
    }

    static void checkMin(Pair[] picks) {
        int result = 0;

        for (Pair house : houses) {
            int tmp = Integer.MAX_VALUE;
            for (Pair chicken : picks) {
                int d = calculateDistance(house, chicken);
                if (d < tmp) tmp = d;
            }
            result += tmp;
        }

        if (result < min) min = result;
    }

    static void comb(int cnt, int start, Pair[] picks) {
        if (cnt == M) {
            checkMin(picks);
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            picks[cnt] = chickens.get(i);
            comb(cnt + 1, i + 1, picks);
        }

    }

    static int calculateDistance(Pair p1, Pair p2) {
        return Math.abs(p1.y - p2.y) + Math.abs(p1.x - p2.x);
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
