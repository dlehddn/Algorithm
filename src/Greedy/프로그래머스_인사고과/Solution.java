package Greedy.프로그래머스_인사고과;

import java.util.*;

class Solution {
    PriorityQueue<Employee> pq =
        new PriorityQueue<>((e1, e2) -> Integer.compare(e2.scoreA + e2.scoreB, e1.scoreA + e1.scoreB));
    Set<Employee> targets = new HashSet<>();
    List<Employee> employees = new ArrayList<>();
    int[] suffixSum;

    public int solution(int[][] scores) {
        suffixSum = new int[scores.length];
        for (int i = 0; i < scores.length; i++) {
            int[] score = scores[i];
            employees.add(new Employee(score[0], score[1], i));
        }
        employees.sort((e1, e2) -> {
            if (e1.scoreA != e2.scoreA) {
                return Integer.compare(e1.scoreA, e2.scoreA);
            } else {
                return Integer.compare(e2.scoreB, e1.scoreB);
            }
        });
        for (int i = employees.size() - 2; i >= 0; i--) {
            suffixSum[i] = Math.max(suffixSum[i + 1], employees.get(i + 1).scoreB);
        }
        for (int i = 0; i < employees.size(); i++) {
            if (i == employees.size() - 1) {
                targets.add(employees.get(i));
                break;
            }
            if (employees.get(i).scoreB >= suffixSum[i]) {
                targets.add(employees.get(i));
            }
        }
        boolean flag = false;
        for (Employee e: targets) {
            if (e.idx == 0) {
                flag = true;
                break;
            }
        }
        if (!flag) return -1;

        int rank = 0;
        int same = 0;
        int sum = -1;

        for (Employee e: targets) {
            pq.add(e);
        }

        while (!pq.isEmpty()) {
            Employee poll = pq.poll();
            int score = poll.scoreA + poll.scoreB;
            if (score != sum) {
                rank++;
                rank += same;
                same = 0;
                sum = score;
            } else {
                same++;
            }

            if (poll.idx == 0) {
                break;
            }
        }
        return rank;
    }

    static class Employee {
        int scoreA, scoreB;
        int idx;

        public Employee(int scoreA, int scoreB, int idx) {
            this.scoreA = scoreA;
            this.scoreB = scoreB;
            this.idx = idx;
        }
    }
}
