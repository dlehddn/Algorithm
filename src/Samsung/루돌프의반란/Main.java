package Samsung.루돌프의반란;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static Santa[] santas;
    static Pair rudolph;
    static int[] dy4 = {-1, 0, 1, 0};
    static int[] dx4 = {0, 1, 0, -1};
    static int[] dy8 = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dx8 = {-1, 0, 1, 1, 1, 0, -1, -1};
    static int N, M, P, C, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        santas = new Santa[P + 1];
        st = new StringTokenizer(br.readLine());
        int rY = Integer.parseInt(st.nextToken());
        int rX = Integer.parseInt(st.nextToken());
        rudolph = new Pair(rY - 1, rX - 1);
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int sN = Integer.parseInt(st.nextToken());
            int sY = Integer.parseInt(st.nextToken());
            int sX = Integer.parseInt(st.nextToken());
            santas[sN] = new Santa(sY - 1, sX - 1);
        }

        for (int i = 1; i <= M; i++) {
            rMove(i);
            sMove(i);
            if (isEnd()) break;
            addPoint();
        }
        printResult();

    }

    static void printResult() {
        StringBuilder sb = new StringBuilder();
        for (Santa s : santas) {
            if (s == null) continue;
            sb.append(s.score + " ");
        }
        System.out.println(sb.toString());
    }

    static void addPoint() {
        for (Santa s : santas) {
            if (s == null) continue;
            if (!s.isOut) s.score++;
        }
    }

    static boolean isEnd() {
        for (Santa s : santas) {
            if (s == null) continue;
            if (!s.isOut) return false;
        }
        return true;
    }

    static void sMove(int t) {
        for (Santa s : santas) {
            if (s == null || s.isOut || s.sternEnd > t) continue;
            int curD = calculateDistance(s.y, s.x, rudolph.y, rudolph.x);
            int min = curD;
            List<Pair> list = new ArrayList<>();
            loop:
            for (int i = 0; i < 4; i++) {
                int nY = s.y + dy4[i];
                int nX = s.x + dx4[i];
                if (outOfRange(nY, nX)) continue;
                for (Santa next : santas) {
                    if (next == null || next == s) continue;
                    if (next.y == nY && next.x == nX) continue loop;
                }
                int d = calculateDistance(nY, nX, rudolph.y, rudolph.x);
                if (d <= min) {
                    if (d < min) {
                        list.clear();
                        min = d;
                    }
                    list.add(new Pair(dy4[i], dx4[i]));
                }
            }
            if (list.size() == 0) continue;
            Pair p = list.get(0);
            s.y += p.y;
            s.x += p.x;
            if (s.y == rudolph.y && s.x == rudolph.x) {
                s.score += D;
                step5(new Pair(s.y + D * p.y * -1, s.x + D * p.x * -1),
                        new Pair(p.y * -1, p.x * -1));
                s.y += D * p.y * -1;
                s.x += D * p.x * -1;
                if (outOfRange(s.y, s.x)) {
                    s.isOut = true;
                }
                s.sternEnd = t + 2;
            }
        }
    }

    static void rMove(int t) {
        List<Santa> list = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for (Santa s : santas) {
            if (s == null) continue;
            if (s.isOut) continue;
            int d = calculateDistance(rudolph.y, rudolph.x, s.y, s.x);
            if (d <= min) {
                if (d < min) {
                    list.clear();
                    min = d;
                }
                list.add(s);
            }
        }
        list.sort((s1, s2) -> {
            if (s1.y != s2.y) {
                return s2.y - s1.y;
            } else {
                return s2.x - s1.x;
            }
        });
        realRMove(list.get(0), t);
    }

    static void realRMove(Santa target, int t) {
        Pair p = null;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 8; i++) {
            int nY = rudolph.y + dy8[i];
            int nX = rudolph.x + dx8[i];
            if (outOfRange(nY, nX)) continue;
            int d = calculateDistance(nY, nX, target.y, target.x);
            if (d < min) {
                p = new Pair(dy8[i], dx8[i]);
                min = d;
            }
        }
        rudolph.y += p.y;
        rudolph.x += p.x;
        for (Santa s : santas) {
            if (s == null) continue;
            if (rudolph.y == s.y && rudolph.x == s.x) {
                s.score += C;
                step5(new Pair(s.y + C * p.y, s.x + C * p.x), p);
                s.y += C * p.y;
                s.x += C * p.x;
                s.sternEnd = t + 2;
                if (outOfRange(s.y, s.x)) s.isOut = true;
                break;
            }
        }
    }

    static void step5(Pair target, Pair d) {
        if (outOfRange(target.y, target.x)) return;
        for (Santa s : santas) {
            if (s == null) continue;
            if (target.y == s.y && target.x == s.x) {
                step5(new Pair(target.y + d.y, target.x + d.x), d);
                s.y += d.y;
                s.x += d.x;
                if (outOfRange(s.y, s.x)) s.isOut = true;
                break;
            }
        }
    }

    static boolean outOfRange(int y, int x) {
        return y < 0 || x < 0 || y >= N || x >= N;
    }

    static int calculateDistance(int y1, int x1, int y2, int x2) {
        return (int) Math.pow(y1 - y2, 2) + (int) Math.pow(x1 - x2, 2);
    }

    static class Santa {
        int y, x, score, sternEnd;
        boolean isOut;

        public Santa(int y, int x) {
            this.y = y;
            this.x = x;
            this.score = 0;
            this.sternEnd = 0;
            this.isOut = false;
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