package BinarySearch.프로그래머스_징검다리;

import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int[] allRocks = new int[rocks.length + 2];
        for (int i = 0; i < rocks.length; i++) {
            allRocks[i + 1] = rocks[i];
        }
        allRocks[allRocks.length - 1] = distance;
        Arrays.sort(allRocks);

        int left = 1;
        int right = distance;
        int result = Integer.MIN_VALUE;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canAccept(allRocks, mid, n)) {
                // System.out.println("accept, mid = " + mid);
                left = mid + 1;
                result = Math.max(result, mid);
            } else {
                // System.out.println("fail, mid = " + mid);
                right = mid - 1;
            }
        }
        return result;
    }

    static boolean canAccept(int[] rocks, int target, int n) {
        int left = 0;
        int right = 1;
        while (right < rocks.length) {
            int d = rocks[right] - rocks[left];
            if (d >= target) {
                left = right;
            } else {
                n--;
            }
            right++;
        }
        if (n >= 0) return true;
        else return false;
    }
}