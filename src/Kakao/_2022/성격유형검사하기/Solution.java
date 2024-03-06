package Kakao._2022.성격유형검사하기;

import java.util.HashMap;
import java.util.Map;

class Solution {
    static String answer;
    static Map<Integer, Map<Character, Integer>> map;

    public static String solution(String[] survey, int[] choices) {
        map = new HashMap<>();
        map.put(1, new HashMap<>());
        map.put(2, new HashMap<>());
        map.put(3, new HashMap<>());
        map.put(4, new HashMap<>());

        for (int i = 0; i < survey.length; i++) {
            String s = survey[i];
            int n = choices[i];
            int key = 0;
            if (s.startsWith("R") || s.startsWith("T")) {
                key = 1;
            } else if (s.startsWith("F") || s.startsWith("C")) {
                key = 2;
            } else if (s.startsWith("M") || s.startsWith("J")) {
                key = 3;
            } else {
                key = 4;
            }
            Map<Character, Integer> target = map.get(key);
            if (n >= 1 && n <= 3) {
                 target.put(s.charAt(0),
                         target.getOrDefault(s.charAt(0), 0) + 3 - n + 1);
            } else if (n >= 5 && n <= 7) {
                target.put(s.charAt(1),
                         target.getOrDefault(s.charAt(1), 0) + n - 4);
            }

        }
        Character c1 = select(1, 'R', 'T');
        Character c2 = select(2, 'C', 'F');
        Character c3 = select(3, 'J', 'M');
        Character c4 = select(4, 'A', 'N');

        StringBuilder sb = new StringBuilder();
        sb.append(c1);
        sb.append(c2);
        sb.append(c3);
        sb.append(c4);
        return sb.toString();
    }

    static Character select(int i, Character first, Character second) {
        Map<Character, Integer> target = map.get(i);
        Integer score1 = target.getOrDefault(first, 0);
        Integer score2 = target.getOrDefault(second, 0);
        if (score1 >= score2) {
            return first;
        } else {
            return second;
        }
    }
}
