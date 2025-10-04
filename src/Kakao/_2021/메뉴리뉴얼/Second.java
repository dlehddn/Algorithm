package Kakao._2021.메뉴리뉴얼;

//start 9:23
//end 9:56
// -> 33m 소요
import java.util.*;

// 조합 사용 시 시간 복잡도
// orders 마다 주어진 course에 맞는 조합 찾기 => 20 * 10 * 252 ~= 50000
class Second {
    List<String> answer = new ArrayList<>();
    Map<String, Integer> orderMap = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        for (String order: orders) {
            for (int pick: course) {
                if (pick <= order.length()) {
                    comb(0, pick, new StringBuilder(), order, 0);
                }
            }
        }
        for (int size: course) {
            int max = 0;
            List<String> tmp = new ArrayList<>();

            for (String key: orderMap.keySet()) {
                int orderCount = orderMap.get(key);
                if (key.length() == size && orderCount >= 2 && orderCount >= max) {
                    if (orderCount > max) {
                        max = orderCount;
                        tmp.clear();
                    }
                    tmp.add(key);
                }
            }
            answer.addAll(tmp);
        }
        answer.sort(Comparator.naturalOrder());
        String[] result = new String[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }
        return result;
    }

    private void comb(int cnt, int pick, StringBuilder sb, String order, int start) {
        if (cnt == pick) {
            List<Character> tmp = new ArrayList<>();
            for (int i = 0; i < pick; i++) {
                tmp.add(sb.charAt(i));
            }
            tmp.sort(Comparator.naturalOrder());
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < pick; i++) {
                sb2.append(tmp.get(i));
            }
            String course = sb2.toString();
            orderMap.put(course, orderMap.getOrDefault(course, 0) + 1);
            return;
        }

        for (int i = start; i < order.length(); i++) {
            sb.append(order.charAt(i));
            comb(cnt + 1, pick, sb, order, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
