package Toss2024.q2;


import java.util.*;

class Solution {

    static int max = -1;

    public int solution(String s) {
        int left = 0;
        int right = 3;

        while (left <= s.length() - 3) {
            String sub = s.substring(left, right);
            if (check(sub)) {
                if (max < Integer.parseInt(sub)) {
                    max = Integer.parseInt(sub);
                }
                System.out.println(sub);
            }
            left++;
            right++;
        }

        return max;
    }

    static boolean check(String s) {
        return s.charAt(0) == s.charAt(1) && s.charAt(0) == s.charAt(2);
    }
}