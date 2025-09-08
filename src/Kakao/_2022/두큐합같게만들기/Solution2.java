package Kakao._2022.두큐합같게만들기;

import java.util.*;
class Solution2 {
    Queue<Integer> q1 = new ArrayDeque();
    Queue<Integer> q2 = new ArrayDeque();
    public int solution(int[] queue1, int[] queue2) {

        long q1Sum = 0;
        long q2Sum = 0;
        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q1Sum += queue1[i];
            q2.add(queue2[i]);
            q2Sum += queue2[i];
        }
        int maxAttempt = queue1.length * 2;
        int answer = 0;
        while (maxAttempt > 0) {
            if (q1Sum == q2Sum) {
                return answer;
            } else if (q1Sum > q2Sum) {
                int poll = q1.poll();
                q2.add(poll);
                q1Sum -= poll;
                q2Sum += poll;
            } else {
                int poll = q2.poll();
                q1.add(poll);
                q1Sum += poll;
                q2Sum -= poll;
            }
            maxAttempt--;
            answer++;
        }
        return -1;
    }
}
