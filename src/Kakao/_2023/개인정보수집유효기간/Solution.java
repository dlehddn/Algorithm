package Kakao._2023.개인정보수집유효기간;

// start 02:53
// end 03:12
// -> 19m 소요
import java.util.*;

class Solution {
    Map<String, Integer> termsMap = new HashMap<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        for (String t: terms) {
            String[] splited = t.split(" ");
            termsMap.put(splited[0], Integer.parseInt(splited[1]) * 28);
        }
        List<Integer> result = new ArrayList<>();
        int now = toDay(today);
        for(int i = 0; i < privacies.length; i++) {
            String[] splited = privacies[i].split(" ");
            int startDay = toDay(splited[0]);
            String type = splited[1];
            if (startDay + termsMap.get(type) <= now) {
                result.add(i + 1);
            }
        }
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
    }

    private int toDay(String day) {
        String[] splited = day.split("\\.");
        int sum = 0;
        sum += Integer.parseInt(splited[2]);
        sum += Integer.parseInt(splited[1]) * 28;
        sum += Integer.parseInt(splited[0]) * 28 * 12;
        return sum;
    }
}