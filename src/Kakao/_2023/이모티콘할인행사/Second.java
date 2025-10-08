package Kakao._2023.이모티콘할인행사;

// start 9:59
// end 10:15
// 16m 소요
import java.util.*;

class Second {
    // 이모티콘 조합 수 = 4^7
    int[] result = new int[2];
    public int[] solution(int[][] users, int[] emoticons) {
        combination(0, emoticons, new int[emoticons.length], users);
        return result;
    }

    private void combination(int idx, int[] emoticons, int[] discountPercents, int[][] users) {
        if (idx == emoticons.length) {
            int emoticonPlusCnt = 0;
            int sales = 0;
            for (int[] user: users) {
                int sum = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    if (discountPercents[i] >= user[0]) {
                        sum += emoticons[i] * (100 - discountPercents[i]) / 100;
                    }
                }
                if (sum >= user[1]) {
                    emoticonPlusCnt++;
                } else {
                    sales += sum;
                }
            }
            if (emoticonPlusCnt > result[0]) {
                result[0] = emoticonPlusCnt;
                result[1] = sales;
            } else if (emoticonPlusCnt == result[0] && sales > result[1]) {
                result[1] = sales;
            }
            return;
        }

        for (int discountPercent = 10; discountPercent <= 40; discountPercent += 10) {
            discountPercents[idx] = discountPercent;
            combination(idx + 1, emoticons, discountPercents, users);
        }
    }
}
