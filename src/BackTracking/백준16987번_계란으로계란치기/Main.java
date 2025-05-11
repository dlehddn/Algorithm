package BackTracking.백준16987번_계란으로계란치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, result;
    static Egg[] eggs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int hp = Integer.parseInt(st.nextToken());
            int weigh = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(hp, weigh);
        }
        solution(0);
        System.out.println(result);
    }

    static void solution(int curEgg) {
        if (curEgg == N) {
            int tmp = 0;
            for (int i = 0; i < N; i++) {
                if (eggs[i].hp <= 0) {
                    tmp++;
                }
            }
            result = Math.max(result, tmp);
            return;
        }

        for (int nextEgg = 0; nextEgg < N; nextEgg++) {
            if (nextEgg == curEgg) continue;
            boolean flag = false;
            if (eggs[curEgg].hp > 0 && eggs[nextEgg].hp > 0) {
                eggs[curEgg].hp -= eggs[nextEgg].weigh;
                eggs[nextEgg].hp -= eggs[curEgg].weigh;
                flag = true;
            }
            solution(curEgg + 1);
            if (flag) {
                eggs[curEgg].hp += eggs[nextEgg].weigh;
                eggs[nextEgg].hp += eggs[curEgg].weigh;
            }
        }
    }

    static class Egg {
        int hp, weigh;

        public Egg(int hp, int weigh) {
            this.hp = hp;
            this.weigh = weigh;
        }
    }
}
