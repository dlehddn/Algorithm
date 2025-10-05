package Kakao._2021.광고삽입;

// start 7:45
// end 9:12
// -> 1h 27m 소요
import java.util.*;
// 총 타임은 100h 미만 -> 초로 표시하면? 360,000
// 총 시청자 로그 -> 300,000개
// 만약 모든 초에 대해서 배열을 만들고, 모든 시청자 로그를 기록하면 시간 초과
//
class Second {
    public String solution(String play_time, String adv_time, String[] logs) {
        PriorityQueue<PlayTime> waitQ =
            new PriorityQueue<>(Comparator.comparingInt(p -> p.start));
        PriorityQueue<PlayTime> enterQ =
            new PriorityQueue<>(Comparator.comparingInt(p -> p.end));
        for (String log: logs) {
            String[] splited = log.split("-");
            waitQ.add(new PlayTime(toSecond(splited[0]), toSecond(splited[1])));
        }

        int playTime = toSecond(play_time);
        int advTime = toSecond(adv_time);
        int[] cntAtSeconds = new int[playTime + 1];
        for (int curTime = 0; curTime <= playTime; curTime++) {
            while (!waitQ.isEmpty() && waitQ.peek().start == curTime - 1) {
                enterQ.add(waitQ.poll());
            }
            while (!enterQ.isEmpty() && enterQ.peek().end < curTime) {
                enterQ.poll();
            }
            cntAtSeconds[curTime] = 1 * enterQ.size();
        }

        long sum = 0;
        for (int curTime = 0; curTime < advTime; curTime++) {
            sum += cntAtSeconds[curTime];
        }

        int left = 1;
        int right = advTime;
        long max = sum;
        String answer = "00:00:00";

        while (right <= playTime) {
            sum += cntAtSeconds[right];
            sum -= cntAtSeconds[left - 1];
            if (sum > max) {
                max = sum;
                answer = toTime(left - 1);
            }
            left++;
            right++;
        }
        return answer;
    }

    static class PlayTime {
        int start, end;

        public PlayTime(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private int toSecond(String time) {
        String[] splited = time.split(":");
        int sum = 0;
        sum += Integer.parseInt(splited[0]) * 60 * 60;
        sum += Integer.parseInt(splited[1]) * 60;
        sum += Integer.parseInt(splited[2]);
        return sum;
    }

    private String toTime(int second) {
        int h = second / 3600;
        second %= 3600;
        int m = second / 60;
        int s = second %= 60;

        String hs = h < 10 ? "0" + Integer.toString(h) : Integer.toString(h);
        String ms = m < 10 ? "0" + Integer.toString(m) : Integer.toString(m);
        String ss = s < 10 ? "0" + Integer.toString(s) : Integer.toString(s);
        return hs + ":" + ms + ":" + ss;
    }
}
