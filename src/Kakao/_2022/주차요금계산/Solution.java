package Kakao._2022.주차요금계산;

// start 1:17
// end 1:50
// total -> 33m
import java.util.*;

class Solution {
    static Map<Integer, Integer> enters = new HashMap<>();
    static Map<Integer, Integer> totalTimes = new HashMap<>();

    public int[] solution(int[] fees, String[] records) {
        for (String record: records) {
            String[] splited = record.split(" ");
            int carNumber = Integer.parseInt(splited[1]);
            if (splited[2].equals("IN")) {
                enters.put(carNumber, transferToMinute(splited[0]));
            } else {
                int enterTime = enters.get(carNumber);
                int outTime = transferToMinute(splited[0]);
                totalTimes.put(carNumber, totalTimes.getOrDefault(carNumber, 0) + (outTime - enterTime));
                enters.remove(carNumber);
            }
        }
        for (Integer carNumber: enters.keySet()) {
            int enterTime = enters.get(carNumber);
            int outTime = transferToMinute("23:59");
            totalTimes.put(carNumber, totalTimes.getOrDefault(carNumber, 0) + (outTime - enterTime));
        }
        List<ParkInfo> parkInfos = new ArrayList<>();
        for (Integer carNumber: totalTimes.keySet()) {
            parkInfos.add(new ParkInfo(carNumber, totalTimes.get(carNumber)));
        }
        parkInfos.sort(Comparator.comparingInt(p -> p.carNumber));

        int[] answer = new int[parkInfos.size()];
        for(int i = 0; i < parkInfos.size(); i++) {
            ParkInfo parkInfo = parkInfos.get(i);
            int cost = fees[1];
            int remainMinute = parkInfo.minute - fees[0];
            if (remainMinute > 0) {
                int x = remainMinute / fees[2];
                if (remainMinute % fees[2] != 0) {
                    x++;
                }
                cost += x * fees[3];
            }
            answer[i] = cost;
        }
        return answer;
    }

    static class ParkInfo {
        int carNumber;
        int minute;

        public ParkInfo(int carNumber, int minute) {
            this.carNumber = carNumber;
            this.minute = minute;
        }
    }

    private int transferToMinute(String time) {
        int h = Integer.parseInt(time.substring(0, 2));
        int m = Integer.parseInt(time.substring(3));
        return 60 * h + m;
    }
}