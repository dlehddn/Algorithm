package Kakao._2024.카드게임;

// start 10:37
// end 10:59
// 22m 소요

import java.util.*;
class Second {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;

        Set<Integer> freeCards = new HashSet<>();
        Set<Integer> nonFreeCards = new HashSet<>();

        for (int i = 0; i < cards.length / 3; i++) {
            freeCards.add(cards[i]);
        }

        int round = 1;
        int topIdx = cards.length / 3;

        while (true) {
            if (topIdx >= cards.length) {
                break;
            }
            for (int i = 0; i < 2; i++) {
                nonFreeCards.add(cards[topIdx]);
                topIdx++;
            }

            boolean flag = false;
            Integer removeTarget1 = 0;
            Integer removeTarget2 = 0;
            for (Integer freeCard1 : freeCards) {
                if (freeCards.contains(n + 1 - freeCard1)) {
                    removeTarget1 = freeCard1;
                    removeTarget2 = n + 1 - freeCard1;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                freeCards.remove(removeTarget1);
                freeCards.remove(removeTarget2);
                round++;
                continue;
            }

            if (coin < 1) break;
            for (Integer freeCard : freeCards) {
                if (nonFreeCards.contains(n + 1 - freeCard)) {
                    removeTarget1 = freeCard;
                    removeTarget2 = n + 1 - freeCard;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                freeCards.remove(removeTarget1);
                nonFreeCards.remove(removeTarget2);
                coin--;
                round++;
                continue;
            }

            if (coin < 2) break;
            for (Integer nonFreeCard : nonFreeCards) {
                if (nonFreeCards.contains(n + 1 - nonFreeCard)) {
                    removeTarget1 = nonFreeCard;
                    removeTarget2 = n + 1 - nonFreeCard;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                nonFreeCards.remove(removeTarget1);
                nonFreeCards.remove(removeTarget2);
                coin -= 2;
                round++;
            } else {
                break;
            }
        }
        return round;
    }
}
