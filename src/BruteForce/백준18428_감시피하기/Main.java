package BruteForce.백준18428_감시피하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static List<Pair> blanks;
    static List<Pair> teachers;
    static char[][] map;
    static String result = "NO";
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        blanks = new ArrayList<>();
        teachers = new ArrayList<>();
        map = new char[N][N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                char c = st.nextToken().charAt(0);
                map[i][j] = c;
                if (c == 'X') {
                    blanks.add(new Pair(i, j));
                } else if (c == 'T') {
                    teachers.add(new Pair(i, j));
                }
            }
        }

        comb(0, 0, new Pair[3]);
        System.out.println(result);
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }

    static boolean checkPass(Pair[] picks) {
        boolean flag = true;
        for (Pair pick : picks) {
            map[pick.y][pick.x] = 'O';
        }
        exit:
        for (Pair teacher : teachers) {
            loop:
            for (int i = 0; i < 4; i++) {
                for (int j = 1; j <= N; j++) {
                    int nY = teacher.y + j * dy[i];
                    int nX = teacher.x + j * dx[i];
                    if (outOfRange(nY, nX) || map[nY][nX] == 'O') continue loop;
                    if (map[nY][nX] == 'S') {
                        flag = false;
                        break exit;
                    }
                }
            }
        }
        for (Pair pick : picks) {
            map[pick.y][pick.x] = 'X';
        }
        return flag;
    }

    static void comb(int cnt, int start, Pair[] picks) {
        if (cnt == 3) {
            if (checkPass(picks)) {
                result = "YES";
            }
            return;
        }

        for (int i = start; i < blanks.size(); i++) {
            picks[cnt] = blanks.get(i);
            if (result.equals("NO")) {
                comb(cnt + 1, i + 1, picks);
            }
        }
    }

    static void clearMap() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'O') {
                    map[i][j] = 'X';
                }
            }
        }
    }

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
