package Comb_Per.백준1107번_리모콘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int target, brokenCnt, min;
    static Set<Integer> brokenButtons = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        brokenCnt = Integer.parseInt(br.readLine());
        if (brokenCnt != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < brokenCnt; i++) {
                brokenButtons.add(Integer.parseInt(st.nextToken()));
            }
        }
        min = Integer.MAX_VALUE;
        for (int i = 1; i <= String.valueOf(target).length() + 1; i++) {
            per(0, i, new StringBuilder());
        }
        min = Math.min(min, Math.abs(100 - target));
        System.out.println(min);
    }

    static void per(int cnt, int targetCnt, StringBuilder tmp) {
        if (cnt == targetCnt) {
            int num = Integer.parseInt(tmp.toString());
            min = Math.min(min, Math.abs(target - num) + targetCnt);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (cnt == 0 && i == 0 && targetCnt != 1) continue;
            if (!brokenButtons.contains(i)) {
                tmp.append(i);
                per(cnt + 1, targetCnt, tmp);
                tmp.deleteCharAt(tmp.length() - 1);
            }
        }
    }
}
