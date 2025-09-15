package Kakao._2024.가장많이받은선물;

// 5:45 start
// 6:05 end
// -> 20m 소요
import java.util.*;

// 1. input name -> 내가 지금까지 선물 준 정보를 알아야함, 누구에게 몇 개 줬는지
// 2. input name -> 나의 선물 지수를 알아야함.
// ** (1)을 만들 때 (2)와의 동기화를 고려해야함.
class Solution {
    Map<String, Map<String, Integer>> giftLogs = new HashMap<>();
    Map<String, Integer> giftScores = new HashMap<>();

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        for(String name: friends) {
            Map<String, Integer> targets = new HashMap<>();
            for(String target: friends) {
                targets.put(target, 0);
            }
            giftLogs.put(name, targets);
            giftScores.put(name, 0);
        }

        for (String gift: gifts) {
            String[] splited = gift.split(" ");
            giftScores.put(splited[0], giftScores.get(splited[0]) + 1);
            giftScores.put(splited[1], giftScores.get(splited[1]) - 1);
            Map<String, Integer> targets = giftLogs.get(splited[0]);
            targets.put(splited[1], targets.get(splited[1]) + 1);
        }
        int max = 0;
        for (String f1: friends) {
            int sum = 0;
            int scoreF1 = giftScores.get(f1);
            for (String f2: friends) {
                if (f1.equals(f2)) continue;
                int f1Tof2 = giftLogs.get(f1).get(f2);
                int f2Tof1 = giftLogs.get(f2).get(f1);
                int scoreF2 = giftScores.get(f2);
                if (f1Tof2 > f2Tof1) {
                    sum++;
                } else if (f1Tof2 == f2Tof1 && scoreF1 > scoreF2) {
                    sum++;
                }
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
