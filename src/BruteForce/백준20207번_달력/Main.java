package BruteForce.백준20207번_달력;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] table = new boolean[1000][366];
    static int N;
    static PriorityQueue<Schedule> schedules = new PriorityQueue<>((s1, s2) -> {
        if (s1.start != s2.start) {
            return s1.start - s2.start;
        } else {
            return (s2.end - s2.start) - (s1.end - s1.start);
        }
    });
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            schedules.add(new Schedule(s, e));
        }

        /**
         * 1. 배치 할 수 있는 곳으로 배치
         * 2. 끊겼다면? 이전까지의 height, length 계산
         * 3. 마지막에 남은거 계산
         */
        int heigh = 0;
        int start = 0;
        int end = -1;
        int result = 0;
        while (!schedules.isEmpty()) {
            Schedule poll = schedules.poll();
            if (end + 1 < poll.start) {
                result += heigh * (end - start + 1);
                heigh = 0;
                start = poll.start;
            }
            for (int i = 0; i < 1000; i++) {
                if (canPutSchedule(poll, i)) {
                    putSchedule(poll, i);
                    heigh = Math.max(heigh, i + 1);
                    end = Math.max(end, poll.end);
                    break;
                }
            }
        }
        result += heigh * (end - start + 1);
        System.out.println(result);
    }

    static void putSchedule(Schedule schedule, int height) {
        for (int i = schedule.start; i <= schedule.end; i++) {
            table[height][i] = true;
        }
    }

    static boolean canPutSchedule(Schedule schedule, int height) {
        for (int i = schedule.start; i <= schedule.end; i++) {
            if (table[height][i]) {
                return false;
            }
        }
        return true;
    }

    static class Schedule {
        int start, end;

        public Schedule(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
