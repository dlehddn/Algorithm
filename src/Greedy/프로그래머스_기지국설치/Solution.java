package Greedy.프로그래머스_기지국설치;

import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int prevStationEnd = 0;
        for (int s: stations) {
            int currentStationStart = Math.max(0, s - w);
            int currentStationEnd = Math.min(n, s + w);

            int requiredStart = prevStationEnd + 1;
            int requiredEnd = currentStationStart - 1;
            if (requiredStart > requiredEnd) {
                prevStationEnd = Math.max(prevStationEnd, currentStationEnd);
                continue;
            }
            int length = requiredEnd - requiredStart + 1;
            int unit = 1 + 2*w;
            answer += length / unit;
            if (length % unit != 0) {
                answer++;
            }
            prevStationEnd = Math.max(prevStationEnd, currentStationEnd);
        }
        if (prevStationEnd != n) {
            int finalStart = prevStationEnd + 1;
            int finalEnd = n;
            int length = finalEnd - finalStart + 1;
            int unit = 1 + 2*w;
            answer += length / unit;
            if (length % unit != 0) {
                answer++;
            }
        }
        return answer;
    }
}
