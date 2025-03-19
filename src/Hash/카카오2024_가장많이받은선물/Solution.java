package Hash.카카오2024_가장많이받은선물;

import java.util.*;
class Solution {
    Map<String, Integer> giftScores = new HashMap<>();
    Map<String, Map<String, Integer>> gives = new HashMap<>();

    public int solution(String[] friends, String[] gifts) {
        for (String name : friends) {
            giftScores.put(name, 0);
            gives.put(name, new HashMap<>());
        }
        StringTokenizer st;
        for (String record: gifts) {
            st = new StringTokenizer(record);
            String from = st.nextToken();
            String to = st.nextToken();
            giftScores.put(from, giftScores.get(from) + 1);
            giftScores.put(to, giftScores.get(to) - 1);
            Map<String, Integer> map = gives.get(from);
            map.put(to, map.getOrDefault(to, 0) + 1);
        }

        int result = Integer.MIN_VALUE;
        for (String name1: friends) {
            int tmp = 0;
            for (String name2: friends) {
                if (name1.equals(name2)) continue;
                int aToB = gives.get(name1).getOrDefault(name2, 0);
                int BtoA = gives.get(name2).getOrDefault(name1, 0);
                if (aToB == BtoA) {
                    int aScore = giftScores.get(name1);
                    int bScore = giftScores.get(name2);
                    if (aScore > bScore) {
                        tmp++;
                    }
                } else if (aToB > BtoA) {
                    tmp++;
                }
            }
            result = Math.max(result, tmp);
        }
        return result;
    }
}