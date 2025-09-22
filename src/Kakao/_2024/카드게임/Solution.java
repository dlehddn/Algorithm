package Kakao._2024.카드게임;

import java.util.*;
// start 1:57
// end 2:23
// -> 26m 소요

class Solution {
    // 종료조건: 1. 카드 뭉치에 남는 카드가 없는 경우
    // 2. 카드 두 장을 낼 수 없는 경우
    // 완전 탐색하면? 300C2 * 500 = 300 * 150 * 500 = 22500000
    List<Card> picks = new ArrayList<>();

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        for (int i = 0; i < n/3; i++) {
            picks.add(new Card(cards[i], true));
        }
        int round = 1;
        int nextIdx = n/3;

        while (nextIdx < n) {
            picks.add(new Card(cards[nextIdx++], false));
            picks.add(new Card(cards[nextIdx++], false));
            Card target1 = null;
            Card target2 = null;

            loop:
            for(Card c1: picks) {
                for(Card c2: picks) {
                    if (c1 == c2) continue;
                    if (c1.num + c2.num == n + 1) {
                        int required = 0;
                        if (c1.isInitCard == false) {
                            required++;
                        }
                        if (c2.isInitCard == false) {
                            required++;
                        }
                        if (coin >= 2 || coin == 1 && required <= 1 || coin == 0 && required == 0) {
                            target1 = c1;
                            target2 = c2;
                            coin -= required;
                            break loop;
                        }
                    }
                }
            }
            if (target1 == null && target2 == null) {
                return round;
            }
            picks.remove(target1);
            picks.remove(target2);
            round++;
        }

        return round;
    }

    static class Card {
        int num;
        boolean isInitCard;

        public Card(int num, boolean isInitCard) {
            this.num = num;
            this.isInitCard = isInitCard;
        }
    }
}
