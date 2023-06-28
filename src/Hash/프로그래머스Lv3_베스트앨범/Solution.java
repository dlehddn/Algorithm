package Hash.프로그래머스Lv3_베스트앨범;

import java.util.*;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        int num = plays.length;

        for(int i = 0; i < plays.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }  // 장르별 총 몇번 플레이 됐는지 해시맵으로 만들기

        ArrayList<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a,b) -> map.get(b) - map.get(a));
        // 플레이 횟수가 가장 많은 장르부터 리스트에 정렬

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
            int max_value = 0, second_value = 0;
            int max_idx = 0, second_idx = 0;
            for(int j = 0; j < plays.length; j++) {
                if(list.get(i).equals((genres[j]))) {
                    if(plays[j] > max_value) {
                        second_value = max_value;
                        second_idx = max_idx;
                        max_value = plays[j];
                        max_idx = j;
                    }
                    else if(plays[j] > second_value) {
                        second_value = plays[j];
                        second_idx = j;
                    }
                }
            } // 장르별 2개의 곡의 인덱스를 찾는 과정

            result.add(max_idx);
            if(second_value != 0) result.add(second_idx);   // 장르에 속한 곡이 하나라면 하나의 곡만 선택.
        }

        return result.stream()
                .mapToInt(i -> i)
                .toArray();
    }
}