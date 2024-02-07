package Kakao._2024.q4;

import java.util.*;

class Solution {
    static int N, C;
    static List<Integer> initPick, afterPick;
    static Queue<Integer> q;

    public int solution(int coin, int[] cards) {
        initPick = new ArrayList<>();
        afterPick = new ArrayList<>();
        q = new LinkedList<>();
        N = cards.length;
        C = coin;
        for (int i = 0; i < N / 3; i++) {
            initPick.add(cards[i]);
        }
        for (int i = N / 3; i < N; i++) {
            q.add(cards[i]);
        }
        for (int i = 1; i <= N / 2 + 1; i++) {
            if (i == N / 2 + 1 || q.size() == 0) {
                return i;
            }
            for (int j = 0; j < 2; j++) {
                afterPick.add(q.poll());
            }
            if (!pick2InInit()) {
                if (!pick1InInit()) {
                    if (!pick2InAfter()) {
                        return i;
                    }
                }
            }
        }
        return 0;
    }

    static boolean pick2InInit() {
        Integer c1 = null, c2 = null;
        boolean isRemoved = false;
        loop:
        for (int i = 0; i < initPick.size(); i++) {
            for (int j = 0; j < initPick.size(); j++) {
                if(i == j) continue;
                if (initPick.get(i) + initPick.get(j) == N + 1) {
                    isRemoved = true;
                    c1 = initPick.get(i);
                    c2 = initPick.get(j);
                    break loop;
                }
            }
        }
        initPick.remove(c1);
        initPick.remove(c2);
        return isRemoved;
    }

    static boolean pick1InInit() {
        for (int i = 0; i < initPick.size(); i++) {
            Integer matchedNum = N + 1 - initPick.get(i);
            if (afterPick.contains(matchedNum) && C > 0) {
                afterPick.remove(matchedNum);
                C--;
                return true;
            }
        }
        return false;
    }

    static boolean pick2InAfter() {
        C -= 2;
        if(C < 0) return false;
        Integer c1 = null, c2 = null;
        boolean isRemoved = false;
        loop:
        for (int i = 0; i < afterPick.size(); i++) {
            for (int j = 0; j < afterPick.size(); j++) {
                if(i == j) continue;
                if (afterPick.get(i) + afterPick.get(j) == N + 1) {
                    isRemoved = true;
                    c1 = afterPick.get(i);
                    c2 = afterPick.get(j);
                    break loop;
                }
            }
        }
        afterPick.remove(c1);
        afterPick.remove(c2);
        return isRemoved;
    }
}