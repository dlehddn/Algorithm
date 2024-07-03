package Kakao._2021.메뉴리뉴얼;

import java.util.*;

class Solution {

    static List<String> result;
    static Map<String, Integer> map;
    static Set<String>[] set;

    public String[] solution(String[] orders, int[] course) {
        result = new ArrayList<>();
        map = new HashMap<>();
        set = new Set[course.length];
        for (int i = 0; i < course.length; i++) {
            set[i] = new HashSet<>();
        }

        for (String order : orders) {
            for (int i = 2; i <= order.length(); i++) {
                comb(0, 0, i, order, new Character[i]);
            }
        }

        for (int i = 0; i < course.length; i++) {
            int size = course[i];
            int max = 0;

            for (String key : map.keySet()) {

                if (key.length() != size) continue;
                if (map.get(key) > max && map.get(key) >= 2) {
                    max = map.get(key);
                    set[i].clear();
                    set[i].add(key);
                } else if (map.get(key) == max && map.get(key) >= 2) {
                    set[i].add(key);
                }
            }
        }

        for (String key : map.keySet()) {
            System.out.println(key + ", " + map.get(key));
        }

        for (Set<String> s : set) {
                for (String ss : s) {
                    result.add(ss);
                }
        }

        result.sort(Comparator.naturalOrder());

        String[] answer = new String[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    static void comb(int start, int cnt, int max, String order, Character[] choice) {
        if(cnt == max) {
            Character[] tmp = new Character[choice.length];
            for (int i = 0; i < tmp.length; i++) {
                tmp[i] = choice[i];
            }
            String s = charToString(tmp);
            map.put(s, map.getOrDefault(s, 0) + 1);
            return;
        }

        for (int i = start; i < order.length(); i++) {
            choice[cnt] = order.charAt(i);
            comb(i + 1, cnt + 1, max, order, choice);
        }
    }

    static String charToString(Character[] choice) {
        Arrays.sort(choice);
        StringBuilder sb = new StringBuilder();

        for (Character c : choice) {
            if (c != null) {
             sb.append(c);
            }
        }
        return sb.toString();
    }
}
