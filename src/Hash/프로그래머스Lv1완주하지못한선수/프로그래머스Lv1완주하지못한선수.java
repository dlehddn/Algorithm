package Hash.프로그래머스Lv1완주하지못한선수;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

class Solution {
    public String solution(String[] participant, String[] completion) {

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < participant.length; i++) {
            map.putIfAbsent(participant[i], 0);
            map.put(participant[i], map.get(participant[i]) + 1);
        }  // 이름이 각각 몇번 불렸는지에 대한 해시맵 완성

        for(int i = 0; i < completion.length; i++) {
            map.put(completion[i], map.get(completion[i]) - 1);
            if(map.get(completion[i]) == 0) map.remove(completion[i]);
        } // 불린거 맵에서 지우기 --> 최종 남는 하나만 찾으면 된다.

        Iterator<String> iter = map.keySet().iterator();
        return iter.next();
    }
}