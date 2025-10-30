package StackQueue.프로그래머스_과제진행하기;

import java.util.*;
class Solution {
    private static final int DAY_IN_MINUTE = 1440;
    private static final int INF = 1000 * 100;

    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        Task[] tasks = new Task[DAY_IN_MINUTE];
        for (String[] plan: plans) {
            String name = plan[0];
            int startMinute = timeToMinute(plan[1]);
            int cost = Integer.parseInt(plan[2]);
            tasks[startMinute] = new Task(name, startMinute, cost);
        }

        int cnt = 0;
        Task curTask = null;
        Stack<Task> stack = new Stack<>();
        for (int curMinute = 0; curMinute <= DAY_IN_MINUTE + INF; curMinute++) {
            if (curTask != null && curTask.startMinute + curTask.cost == curMinute) {
                answer[cnt++] = curTask.name;
                curTask = null;
            }

            if (curMinute <= DAY_IN_MINUTE - 1 && tasks[curMinute] != null) {
                if (curTask != null) {
                    curTask.cost -= (curMinute - curTask.startMinute);
                    stack.push(curTask);
                }
                curTask = tasks[curMinute];
            }

            if (curTask == null && !stack.isEmpty()) {
                curTask = stack.pop();
                curTask.startMinute = curMinute;
            }
        }
        return answer;
    }

    private int timeToMinute(String time) {
        String[] split = time.split(":");
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
    }

    static class Task {
        String name;
        int startMinute, cost;

        public Task(String name, int startMinute, int cost) {
            this.name = name;
            this.startMinute = startMinute;
            this.cost = cost;
        }
    }
}
