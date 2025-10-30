package BackTracking.프로그래머스_광물캐기;

import java.util.*;
class Solution {
    private int min = Integer.MAX_VALUE;
    private static final String DIA = "diamond";
    private static final String IRON = "iron";
    private static final String STONE = "stone";

    public int solution(int[] picks, String[] minerals) {
        int size = minerals.length / 5;
        if (minerals.length % 5 != 0) {
            size++;
        }
        List<String>[] groups = new List[size];
        for (int i = 0; i < minerals.length; i += 5) {
            List<String> group = new ArrayList<>();
            for (int j = i; j < i + 5 && j < minerals.length; j++) {
                group.add(minerals[j]);
            }
            groups[i / 5] = group;
        }
        backTracking(0, 0, groups, picks);
        return min;
    }

    private void backTracking(int cnt, int prefixSum, List<String>[] groups, int[] picks) {
        if ((picks[0] == 0 && picks[1] == 0 && picks[2] == 0) || cnt == groups.length) {
            min = Math.min(min, prefixSum);
            return;
        }

        List<String> group = groups[cnt];
        if (picks[0] > 0) {
            picks[0]--;
            backTracking(cnt + 1, prefixSum + group.size(), groups, picks);
            picks[0]++;
        }

        if (picks[1] > 0) {
            int sum = 0;
            for (String type: group) {
                if (type.equals(DIA)) {
                    sum += 5;
                } else {
                    sum++;
                }
            }
            picks[1]--;
            backTracking(cnt + 1, prefixSum + sum, groups, picks);
            picks[1]++;
        }

        if (picks[2] > 0) {
            int sum = 0;
            for (String type: group) {
                if (type.equals(DIA)) {
                    sum += 25;
                } else if (type.equals(IRON)) {
                    sum += 5;
                } else {
                    sum++;
                }
            }
            picks[2]--;
            backTracking(cnt + 1, prefixSum + sum, groups, picks);
            picks[2]++;
        }
    }
}