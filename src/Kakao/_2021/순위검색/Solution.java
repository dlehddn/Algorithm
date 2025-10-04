package Kakao._2021.순위검색;

import java.util.*;
// start 10:00
// end 11:00
// -> 1h 소요

// info -> 50, 000
// query -> 100,000
// 점수 -> 100,000
// 한 쿼리에 대해 모두 '-'이고, 특정 점수 이상를 찾아야한다면?
// -> 3 * 2 * 2 * 2 * log(100,000) = 24 * 16 ~= 400
// 결국 모든 쿼리에 대한 복잡도는 4천만정도
// info map만 잘 만들면 된다.
class Solution {
    Map<String, List<Integer>> scoreMap = new HashMap<>();
    String[] langs = {"cpp", "java", "python"};
    String[] types = {"backend", "frontend"};
    String[] years = {"junior", "senior"};
    String[] foods = {"chicken", "pizza"};

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        for (int l = 0; l < 3; l++) {
            for (int t = 0; t < 2; t++) {
                for (int y = 0; y < 2; y++) {
                    for (int f = 0; f < 2; f++) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(langs[l]).append(" ")
                            .append(types[t]).append(" ")
                            .append(years[y]).append(" ")
                            .append(foods[f]);
                        scoreMap.put(sb.toString(), new ArrayList<>());
                    }
                }
            }
        }
        for (String i: info) {
            int lastWhiteSpaceIdx = i.lastIndexOf(" ");
            List<Integer> scores = scoreMap.get(i.substring(0, lastWhiteSpaceIdx));
            scores.add(Integer.parseInt(i.substring(lastWhiteSpaceIdx + 1)));
        }
        for (String key: scoreMap.keySet()) {
            List<Integer> score = scoreMap.get(key);
            score.sort(Comparator.naturalOrder());
        }
        int cnt = 0;
        for (String q: query) {
            String[] splited = q.split(" ");
            String lang = splited[0];
            String type = splited[2];
            String year = splited[4];
            String food = splited[6];
            Integer targetScore = Integer.parseInt(splited[7]);
            int total = 0;
            for (String key: scoreMap.keySet()) {
                if (isConditional(key, lang, type, year, food)) {
                    // 이분탐색으로 몇명인지
                    List<Integer> scores = scoreMap.get(key);
                    int left = 0;
                    int right = scores.size() - 1;
                    int max = 0;
                    while (left <= right) {
                        int mid = (left + right) / 2;
                        if (scores.get(mid) >= targetScore) {
                            max = Math.max(max, scores.size() - 1 - mid + 1);
                            right = mid - 1;
                        } else {
                            left = mid + 1;
                        }
                    }
                    total += max;
                }
            }
            answer[cnt++] = total;
        }
        return answer;
    }

    private boolean isConditional(String key, String lang, String type, String year, String food) {
        String[] splited = key.split(" ");
        if (!lang.equals("-") && !splited[0].equals(lang)) {
            return false;
        }
        if (!type.equals("-") && !splited[1].equals(type)) {
            return false;
        }
        if (!year.equals("-") && !splited[2].equals(year)) {
            return false;
        }
        if (!food.equals("-") && !splited[3].equals(food)) {
            return false;
        }
        return true;
    }
}
