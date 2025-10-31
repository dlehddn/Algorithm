package DP.프로그래머스_가장긴팰린드롬;

// 완전탐색하면? 터짐
// dp? ->
class Solution {
    public int solution(String s) {
        int answer = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
            }
        }
        for (int i = 2; i < s.length(); i++) {
            for (int j = 0; i + j < s.length(); j++) {
                if (s.charAt(j) == s.charAt(j + i) && dp[j + 1][j + i - 1]) {
                    dp[j][j + i] = true;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (dp[i][j]) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }
}
