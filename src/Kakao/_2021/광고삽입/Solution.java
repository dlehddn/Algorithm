package Kakao._2021.광고삽입;

import java.util.*;
class Solution {

    static long[] total_times;
    static int pTime, adTime;
    public String solution(String play_time, String adv_time, String[] logs) {
        pTime = htos(play_time);
        adTime = htos(adv_time);

        total_times = new long[pTime];
        for (String s : logs) {
            int startTime = htos(s.substring(0, 8));
            int endTime = htos(s.substring(9));
            for (int i = startTime; i < endTime; i++) {
                total_times[i]++;
            }
        }

        long sum = 0;
        long max = 0;
        int min = 0;

        for (int i = 0; i < adTime; i++) {
            sum += total_times[i];
        }
        max = sum;

        int left = 0;
        int right = adTime - 1;

        for (int i = 1; i + adTime <= pTime; i++) {
            sum -= total_times[left++];
            sum += total_times[++right];
            if (sum > max) {
                min = i;
                max = sum;
            }
        }
        return stoh(min);
    }

    static int htos(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 3600 +
            Integer.parseInt(time.substring(3, 5)) * 60 +
            Integer.parseInt(time.substring(6));
    }

    static String stoh(int time) {
        StringBuilder sb = new StringBuilder();

        if (time / 3600 < 10) {
            sb.append("0");
        }
        sb.append(time / 3600);
        time %= 3600;
        sb.append(":");

        if (time / 60 < 10) {
            sb.append("0");
        }
        sb.append(time / 60 + ":");
        if (time % 60 < 10) {
            sb.append("0");
        }
        sb.append(time % 60);

        return sb.toString();
    }
}
