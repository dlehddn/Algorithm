package Hash.프로그래머스_주차요금계산;

import java.util.*;

class Solution {
    static Map<Integer, Integer> enterRecords;
    static Map<Integer, Integer> totalTimes;
    static List<Record> totalFees = new ArrayList<>();

    public int[] solution(int[] fees, String[] records) {
        enterRecords = new HashMap<>();
        totalTimes = new HashMap<>();

        StringTokenizer st;
        for (String record: records) {
            st = new StringTokenizer(record);
            String time = st.nextToken();
            int carNumber = Integer.parseInt(st.nextToken());
            String type = st.nextToken();

            if (type.equals("IN")) {
                enterRecords.put(carNumber, transferMinute(time));
            } else {
                int prevTime = enterRecords.get(carNumber);
                enterRecords.remove(carNumber);
                int curTime = transferMinute(time);
                totalTimes.put(carNumber, totalTimes.getOrDefault(carNumber, 0) +
                              curTime - prevTime);
            }
        }

        int endMinute  = transferMinute("23:59");
        for (Integer carNumber: enterRecords.keySet()) {
            int prevTime = enterRecords.get(carNumber);
            totalTimes.put(carNumber, totalTimes.getOrDefault(carNumber, 0) +
                          endMinute - prevTime);
        }

        for (Integer carNumber: totalTimes.keySet()) {
            int totalTime = totalTimes.get(carNumber);
            if (totalTime <= fees[0]) {
                totalFees.add(new Record(carNumber, fees[1]));
            } else {
                int payTime = totalTime - fees[0];
                int tmp = fees[1] + payTime / fees[2] * fees[3];
                if (payTime % fees[2] != 0) {
                    tmp += fees[3];
                }
                totalFees.add(new Record(carNumber, tmp));
            }
        }

        return totalFees.stream()
            .sorted(Comparator.comparingInt(r -> r.carNumber))
            .mapToInt(r -> r.fee)
            .toArray();
    }

    static int transferMinute(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60
            + Integer.parseInt(time.substring(3));
    }

    static class Record {
        int carNumber, fee;

        public Record(int carNumber, int fee) {
            this.carNumber = carNumber;
            this.fee = fee;
        }
    }
}