package Greedy.프로그래머스_숫자블록;

class Solution {
    public int[] solution(long begin, long end) {
        int s = (int) begin;
        int e = (int) end;
        int[] answer = new int[e - s + 1];

        for (int i = s; i <= e; i++) {
            if (i != 1) {
                answer[i - s] = 1;
            }
        }

        for (int i = s; i <= e; i++) {
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    if (i / j <= 10000000) {
                        answer[i - s] = i / j;
                        break;
                    } else {
                        answer[i - s] = Math.max(answer[i - s], i / (i / j));
                    }
                }
            }
        }
        return answer;
    }
}