package Hash.프로그래머스Lv1_달리기경주;
import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < players.length; i++) {
            map.put(players[i], i + 1);
        }         // 현재플레이어 순위 기준으로 Map 초기화

        for(int i = 0; i < callings.length; i++) {
            map.put(callings[i], map.get(callings[i]) - 1);
            map.put(players[map.get(callings[i]) - 1], map.get(callings[i]) + 1);
            //Map에 순위변동 기록

            String tmp = "";
            tmp = players[map.get(callings[i]) - 1];
            players[map.get(callings[i]) - 1] = players[map.get(callings[i])];
            players[map.get(callings[i])] = tmp;
            //바뀐 순위 --> players 배열에 재배치
        }
        return players;
    }
}