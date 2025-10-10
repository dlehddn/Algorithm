package Kakao._2019.실패율;

// start 9:22
// end 9:49
// -> 27m 소요

// 스테이지 수 = 500
// 플레이어 수  = stages.length = 200,000
// 모든 스테이지에 대해서 누적합을 구하면 1억. ->
//
import java.util.*;
class Solution {
    int[] curStages, prefixSum;

    public int[] solution(int N, int[] stages) {
        curStages = new int[N + 2];
        prefixSum = new int[N + 2];

        for (int stage: stages) {
            curStages[stage]++;
        }

        prefixSum[N + 1] = curStages[N + 1];
        for (int i = N; i >= 1; i--) {
            prefixSum[i] = prefixSum[i + 1] + curStages[i];
        }

        List<Stage> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            double failureRate = curStages[i] == 0 ? 0 : (double) curStages[i] / (double) prefixSum[i];
            list.add(new Stage(failureRate, i));
        }

        list.sort((s1, s2) -> {
            if (s1.failureRate != s2.failureRate) {
                return Double.compare(s2.failureRate, s1.failureRate);
            } else {
                return Integer.compare(s1.stage, s2.stage);
            }
        });

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i).stage;
        }
        return answer;
    }

    static class Stage {
        double failureRate;
        int stage;

        public Stage(double failureRate, int stage) {
            this.failureRate = failureRate;
            this.stage = stage;
        }
    }
}
