package Greedy.프로그래머스_최고의집합;

import java.util.*;
class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        if (n > s) {
            return new int[]{-1};
        }
        int mainNum = s / n;

        int exceedCnt = s % n;
        int mainCnt = n - exceedCnt;
        int idx = 0;
        while (mainCnt > 0) {
            answer[idx++] = mainNum;
            mainCnt--;
        }
        while (exceedCnt > 0) {
            answer[idx++] = mainNum + 1;
            exceedCnt--;
        }
        return answer;
    }
}
