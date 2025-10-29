package BinarySearch.프로그래머스_점찍기;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;

        int a = 0;
        while (true) {
            int left = 0;
            int right = d;
            int max = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                double distance = findDistance(a*k, mid);
                if (distance <= d) {
                    int maxB = mid / k;
                    max = Math.max(max, maxB + 1);
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (max == 0) {
                break;
            }
            answer += max;
            a++;
        }
        return answer;
    }

    private double findDistance(int y, int x) {
        return Math.sqrt(Math.pow(y, 2) + Math.pow(x, 2));
    }
}
