package Greedy.카카오2024_n더하기1게임;

import java.util.*;
class Solution {
    static int n, target, remainCoin;
    static List<Integer> firstCards;
    static boolean[] used, deck;

    public int solution(int coin, int[] cards) {
        n = cards.length;
        target = n + 1;
        firstCards = new ArrayList<>();
        remainCoin = coin;
        deck = new boolean[n + 1];
        used = new boolean[n + 1];

        for(int i = 0; i < n/3; i++) {
            firstCards.add(cards[i]);
        }

        int idx = n/3;
        int round = 1;

        while (true) {
            if (idx == n) break;
            deck[cards[idx++]] = true;
            deck[cards[idx++]] = true;
            if (canOriginDeck()) {
                round++;
            } else if (canAnotherDeckJoin()) {
                round++;
            } else {
                // System.out.println("4 진입");
                break;
            }
        }

        return round;
    }
    static boolean canAnotherDeckJoin() {
        Integer removeOrigin = null;
        boolean flag = false;
        for (Integer origin: firstCards) {
            if (deck[target - origin] && !used[target - origin] && remainCoin >= 1) {
                // System.out.println("2 진입");
                used[target - origin] = true;
                remainCoin--;
                removeOrigin = origin;
                break;
            }
        }
        if (removeOrigin != null) {
            firstCards.remove(removeOrigin);
            return true;
        }

        for (int i = 1; i <= n; i++) {
            if (!deck[i] || used[i]) continue;
            if (deck[target - i] && !used[target - i] && remainCoin >= 2) {
                // System.out.println("3 진입");
                remainCoin -= 2;
                used[target - i] = true;
                used[i] = true;
                return true;
            }
        }
        return false;
    }

    static boolean canOriginDeck() {
        Integer num1 = null;
        Integer num2 = null;
        boolean flag = false;
        loop:
        for (Integer n1: firstCards) {
            for (Integer n2: firstCards) {
                if (n1 == n2) continue;
                if (n1 + n2 == target) {
                    // System.out.println("1 진입");
                    num1 = n1;
                    num2 = n2;
                    flag = true;
                    break loop;
                }
            }
        }
        if (flag) {
            firstCards.remove(num1);
            firstCards.remove(num2);
            return true;
        } else {
            return false;
        }
    }
}
