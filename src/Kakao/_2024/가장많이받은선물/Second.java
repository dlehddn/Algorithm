package Kakao._2024.가장많이받은선물;

// start 4:36
// end 4:51
// -> 15m 소요
import java.util.*;
class Second {
    Map<String, Integer> giftScore = new HashMap<>();
    Map<String, Map<String, Integer>> giftBoard = new HashMap<>();

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        for (String f: friends) {
            giftScore.put(f, 0);
            Map<String, Integer> map = new HashMap<>();
            for (String ff: friends) {
                if (!f.equals(ff)) {
                    map.put(ff, 0);
                }
            }
            giftBoard.put(f, map);
        }
        for (String gift: gifts) {
            String[] split = gift.split(" ");
            giftScore.put(split[0], giftScore.get(split[0]) + 1);
            giftScore.put(split[1], giftScore.get(split[1]) - 1);
            Map<String, Integer> toMap = giftBoard.get(split[0]);
            toMap.put(split[1], toMap.get(split[1]) + 1);
        }
        int max = 0;
        for (String f: friends) {
            int tmp = 0;
            Map<String, Integer> toMap = giftBoard.get(f);
            for (String to: toMap.keySet()) {
                int give = toMap.get(to);
                int receive = giftBoard.get(to).get(f);
                if (give > receive ||
                   give == receive && giftScore.get(f) > giftScore.get(to)) {
                    tmp++;
                }
            }
            max = Math.max(max, tmp);
        }
        return max;
    }
}
