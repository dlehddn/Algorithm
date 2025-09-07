package Kakao._2022.k진수에서소수개수구하기;

import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String after = transfer(n, k);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < after.length(); i++) {
            if (after.charAt(i) - '0' == 0 && sb.length() > 0) {
                if (isSosu(Long.parseLong(sb.toString()))) {
                    answer++;
                }
                sb = new StringBuilder();
            } else if (after.charAt(i) - '0' != 0) {
                sb.append(after.charAt(i) - '0');
            }
        }
        if (sb.length() > 0 && isSosu(Long.parseLong(sb.toString()))) {
            answer++;
        }
        return answer;
    }

    private boolean isSosu(long number) {
        if (number == 1) return false;
        for(int start = 2; start <= (int) Math.sqrt(number); start++) {
            if (number % start == 0) {
                return false;
            }
        }
        return true;
    }

    private String transfer(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n >= k) {
            sb.append(n % k);
            n /= k;
        }
        sb.append(n);
        return sb.reverse().toString();
    }
}