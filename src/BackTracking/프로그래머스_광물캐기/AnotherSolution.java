package BackTracking.프로그래머스_광물캐기;

import java.util.*;
class AnotherSolution {
    private static final String DIA = "diamond";
    private static final String IRON = "iron";
    private static final String STONE = "stone";

    public int solution(int[] picks, String[] minerals) {
        int size = minerals.length / 5;
        if (minerals.length % 5 != 0) {
            size++;
        }
        size = Math.min(size, picks[0] + picks[1] + picks[2]);

        List<String>[] groups = new List[size];
        for (int i = 0; i / 5 < size; i += 5) {
            List<String> group = new ArrayList<>();
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                group.add(minerals[j]);
            }
            groups[i / 5] = group;
        }

        Arrays.sort(groups, (g1, g2) -> {
            int sum1 = 0;
            int sum2 = 0;
            for (String e: g1) {
                if (e.equals(DIA)) {
                    sum1 += 36;
                } else if (e.equals(IRON)) {
                    sum1 += 6;
                } else {
                    sum1++;
                }
            }
            for (String e: g2) {
                if (e.equals(DIA)) {
                    sum2 += 36;
                } else if (e.equals(IRON)) {
                    sum2 += 6;
                } else {
                    sum2++;
                }
            }
            return Integer.compare(sum2, sum1);
        });
        int min = 0;
        for (List<String> group: groups) {
            if (picks[0] > 0) {
                picks[0]--;
                min += group.size();
            } else if (picks[1] > 0) {
                picks[1]--;
                for (String type: group) {
                    if (type.equals(DIA)) {
                        min += 5;
                    } else {
                        min++;
                    }
                }
            } else if (picks[2] > 0) {
                picks[2]--;
                for (String type: group) {
                    if (type.equals(DIA)) {
                        min += 25;
                    } else if (type.equals(IRON)){
                        min += 5;
                    } else {
                        min++;
                    }
                }
            } else {
                break;
            }
        }
        return min;
    }
}
