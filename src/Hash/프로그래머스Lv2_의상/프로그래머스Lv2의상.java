package Hash.프로그래머스Lv2_의상;

import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < clothes.length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 1) + 1);
        }


        int product = 0;
        int temp = 1;
        for(int value : map.values()) {
            product = temp * value;
            temp = product;
        }
        int result = product - 1;
        return result;
    }
}
