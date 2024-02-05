package Kakao._2024.q1;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
    static HashMap<String, HashMap<String, Integer>> presentMap;
    static HashMap<String, Integer> scoreMap;

    public int solution(String[] friends, String[] gifts) {
        presentMap = new HashMap<>();
        scoreMap = new HashMap<>();

        for (int i = 0; i < friends.length; i++) {
            presentMap.put(friends[i], new HashMap<>());
            scoreMap.put(friends[i], 0);
        }

        StringTokenizer st;
        for (int i = 0; i < gifts.length; i++) {
            st = new StringTokenizer(gifts[i]);
            String from = st.nextToken();
            String to = st.nextToken();
            // 주고 받은 선물 정리
            HashMap<String, Integer> curMap = presentMap.get(from);
            curMap.put(to, curMap.getOrDefault(to, 0) + 1);
            // 선물 지수 계산
            scoreMap.put(from, scoreMap.get(from) + 1);
            scoreMap.put(to, scoreMap.get(to) - 1);
        }

        int max = 0;
        for (int i = 0; i < friends.length; i++) {
            int tmp = 0;
            String A = friends[i];
            for (int j = 0; j < friends.length; j++) {
                if(i == j) continue;
                String B = friends[j];
                Integer AtoB = presentMap.get(A).getOrDefault(B, 0);
                Integer BtoA = presentMap.get(B).getOrDefault(A, 0);
                if (AtoB != 0 || BtoA != 0) {
                    if (AtoB > BtoA) {
                        tmp++;
                    }
                    continue;
                } if(AtoB == 0 && BtoA == 0 || AtoB == BtoA) {
                    if (scoreMap.get(A) > scoreMap.get(B)) {
                            tmp++;
                    }
                }
            }
            if(tmp > max) max = tmp;
        }
        return max;
    }
}
