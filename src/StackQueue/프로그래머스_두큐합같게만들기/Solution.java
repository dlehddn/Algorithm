package StackQueue.프로그래머스_두큐합같게만들기;

import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = Arrays.stream(queue1)
            .sum();
        long sum2 = Arrays.stream(queue2)
            .sum();
        long goal = (sum1 + sum2) / 2;
        Queue<Integer> q1 = makeQ(queue1);
        Queue<Integer> q2 = makeQ(queue2);

        int cnt = queue1.length + queue2.length + 10;
        int result = 0;

        while (cnt > 0) {
            if (sum1 > sum2) {
                int poll = q1.poll();
                sum1 -= poll;
                sum2 += poll;
                q2.add(poll);
                result++;
            } else if (sum2 > sum1) {
                int poll = q2.poll();
                sum1 += poll;
                sum2 -= poll;
                q1.add(poll);
                result++;
            } else {
                return result;
            }
            cnt--;
        }
        return -1;
    }

    static Queue<Integer> makeQ(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            q.add(arr[i]);
        }
        return q;
    }
}