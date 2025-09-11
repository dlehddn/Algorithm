package Kakao._2023.표현가능한이진트리;

//start 11:19
//end 12:17
import java.util.*;

// 5 -> 101
// 95 -> 1011111
// 1, 3, 7, 15, 31, 63 ...
// 일반항: 2^x-1
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            long n = numbers[i];
            String binary = Long.toBinaryString(n);
            String fullBinary = toFullBinary(binary);
            boolean result = check(fullBinary, false);
            if (result) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        return answer;
    }

    private boolean check(String fullBinary, boolean isZeroRoot) {
        if (isZeroRoot && fullBinary.charAt(fullBinary.length() / 2) == '1') {
            return false;
        }
        if (fullBinary.length() == 1) {
            return true;
        }

        boolean leftSide = true;
        boolean rightSide = true;
        if (fullBinary.charAt(fullBinary.length() / 2) == '0') {
            leftSide = check(fullBinary.substring(0, fullBinary.length() / 2), true);
            rightSide = check(fullBinary.substring(fullBinary.length() / 2 + 1), true);
        } else {
            leftSide = check(fullBinary.substring(0, fullBinary.length() / 2), isZeroRoot);
            rightSide = check(fullBinary.substring(fullBinary.length() / 2 + 1), isZeroRoot);
        }

        if (leftSide && rightSide) {
            return true;
        } else {
            return false;
        }
    }

    private String toFullBinary(String binary) {
        int cur = binary.length();
        int power = (int) (Math.log(cur) / Math.log(2));
        int goal = (int) Math.pow(2, power + 1) - 1;

        StringBuilder sb = new StringBuilder();
        while (cur < goal) {
            sb.append("0");
            cur++;
        }
        sb.append(binary);
        return sb.toString();
    }
}
