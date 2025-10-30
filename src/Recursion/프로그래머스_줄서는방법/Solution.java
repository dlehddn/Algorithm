package Recursion.프로그래머스_줄서는방법;

import java.util.*;

class Solution {
    int[] answer;
    public int[] solution(int n, long k) {
        answer = new int[n];
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        recursion(n , n, k, list);
        return answer;
    }

    private void recursion(int size, int n, long k, List<Integer> numbers) {
        if (n == 1) {
            answer[size - 1] = numbers.get(0);
            return;
        }
        long unit = factor(n - 1);
        int idx = (int) (k / unit);
        if (k % unit != 0) idx++;

        Integer target = null;
        if (idx > 0) {
            target = numbers.get(idx - 1);
        } else {
            target = numbers.get(numbers.size() - 1);
        }
        answer[size - n] = target;
        numbers.remove(target);

        recursion(size, n - 1, k % unit, numbers);
    }

    private long factor(int a) {
        if (a == 1) {
            return 1;
        }
        return (long) a * factor(a - 1);
    }
}
