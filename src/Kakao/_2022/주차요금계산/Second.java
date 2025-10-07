package Kakao._2022.주차요금계산;

// start 5:41
// end 6:05
// -> 24m 소요
import java.util.*;

// 입, 출차를 관리하는 맵 하나
// 누적 시간을 관리하는 맵 하나
class Second {
    Map<String, Integer> enterMap = new HashMap<>();
    Map<String, Integer> totalTimeMap = new HashMap<>();

    public int[] solution(int[] fees, String[] records) {
        int defaultMinute = fees[0];
        int defaultFee = fees[1];
        int unitMinute = fees[2];
        int unitFee = fees[3];

        for (String record: records) {
            String[] split = record.split(" ");
            int time = toMinute(split[0]);
            String carNum = split[1];
            String type = split[2];

            if (type.equals("IN")) {
                enterMap.put(carNum, time);
            } else {
                int enterTime = enterMap.get(carNum);
                totalTimeMap.put(carNum, totalTimeMap.getOrDefault(carNum, 0) + time - enterTime);
                enterMap.remove(carNum);
            }
        }

        int lastOutTime = toMinute("23:59");
        for (String key: enterMap.keySet()) {
            int enterTime = enterMap.get(key);
            totalTimeMap.put(key, totalTimeMap.getOrDefault(key, 0) + lastOutTime - enterTime);
        }

        List<FeeInfo> list = new ArrayList<>();
        for (String key: totalTimeMap.keySet()) {
            int totalMinute = totalTimeMap.get(key);
            int totalFee = defaultFee;
            if (totalMinute > defaultMinute) {
                int exceedMinute = totalMinute - defaultMinute;
                if (exceedMinute % unitMinute == 0) {
                    totalFee += exceedMinute / unitMinute * unitFee;
                } else {
                    totalFee += (exceedMinute / unitMinute + 1) * unitFee;
                }
            }
            list.add(new FeeInfo(Integer.parseInt(key), totalFee));
        }
        list.sort(Comparator.comparingInt(f -> f.carNum));

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).fee;
        }

        return answer;
    }

    static class FeeInfo {
        int carNum, fee;

        public FeeInfo(int carNum, int fee) {
            this.carNum = carNum;
            this.fee = fee;
        }
    }

    private int toMinute(String time) {
        String[] split = time.split(":");
        int h = Integer.parseInt(split[0]);
        int m = Integer.parseInt(split[1]);
        return h * 60 + m;
    }
}
