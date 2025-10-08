package Kakao._2023.개인정보수집유효기간;

// start 8:43
// end 8:55
// -> 12m 소요
import java.util.*;
class Second {
    Map<String, Integer> typeToDays = new HashMap<>();
    public int[] solution(String today, String[] terms, String[] privacies) {
        for (String t: terms) {
            String[] split = t.split(" ");
            typeToDays.put(split[0], Integer.parseInt(split[1]) * 28);
        }
        int todays = toDays(today);
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String p = privacies[i];
            String[] split = p.split(" ");

            int startDays = toDays(split[0]);
            String type = split[1];
            if (startDays + typeToDays.get(type) <= todays) {
                list.add(i + 1);
            }
        }
        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    private int toDays(String today) {
        String[] split = today.split("\\.");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);
        return year * 12 * 28 + month * 28 + day;
    }
}
