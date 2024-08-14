package Samsung.루돌프의반란;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, P, C, D, rY, rX;
    static int[] scores;
    static int[] dy4 = {-1, 0, 1, 0};
    static int[] dx4 = {0, 1, 0, -1};
    static int[] dy8 = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dx8 = {1, 0, -1, 1, -1, 1, 0, -1};
    static Santa[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        scores = new int[P + 1];
        map = new Santa[N][N];
        st = new StringTokenizer(br.readLine());
        rY = Integer.parseInt(st.nextToken()) - 1;
        rX = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            map[y][x] = new Santa(n);
        }

    }

    static int calculateDistance(int[] from, int[] to) {
        return (int) Math.pow(from[0] - to[0], 2) + (int) Math.pow(from[1] - to[1], 2);
    }

    static class Santa {
        int num, reStart;
        boolean isStun;

        public Santa(int num) {
            this.num = num;
            this.reStart = 0;
            this.isStun = false;
        }
    }

}
