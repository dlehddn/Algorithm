package Euclid.백준6064_카잉달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int T, M, N, x, y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        loop:
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            int maxStar = euclid(M, N);
            int minStar = M * N / maxStar;
            if (x > minStar || y > minStar) {
                sb.append(-1).append("\n");
                continue;
            }
            Set<Integer> set = new HashSet<>();
            int tmpX = x;
            while (tmpX <= minStar) {
                set.add(tmpX);
                tmpX += M;
            }
            int tmpY = y;
            while (tmpY <= minStar) {
                if (set.contains(tmpY)) {
                    sb.append(tmpY).append("\n");
                    continue loop;
                }
                tmpY += N;
            }
            sb.append(-1).append("\n");
        }
        System.out.println(sb.toString());
    }

    static int euclid(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return euclid(b, a % b);
    }
}
