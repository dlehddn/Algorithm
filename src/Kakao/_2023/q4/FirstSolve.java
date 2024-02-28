package Kakao._2023.q4;

import java.util.*;
class FirstSolve {
    public int[] solution(long[] numbers) {
        int[] result = new int[numbers.length];

        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] == 1) {
                result[i] = 1;
                continue;
            }
            String binaryCode = makeBinary(numbers[i]);
            result[i] = check(binaryCode, binaryCode.length(), 0, binaryCode.length()-1);
        }
        return result;
    }

    static String makeBinary(long number) {
        String binary = Long.toBinaryString(number);
        int length = binary.length();
        int cnt = 0;
        while(true) {
            length /= 2;
            cnt++;
            if(length < 2) {
                break;
            }
        }
        int plusZero = (int) Math.pow(2, cnt + 1) - 1 - binary.length();
        StringBuilder sb = new StringBuilder();
        while(plusZero > 0) {
            sb.append("0");
            plusZero--;
        }
        return sb.toString() + binary;
    }

    static int check(String number, int size, int start, int end) {
        if(size == 3 && number.charAt(1) == '1') {
            return 1;
        }
        if(number.charAt(size/2) == '0') {
            for(int i = start; i <= end; i++) {
                if(number.charAt(i) == '1') {
                    return 0;
                }
            }
            return 1;
        } else {
            String left = number.substring(0, size/2);
            String right = number.substring(size/2 + 1);
            return check(left, left.length(), 0, size/2 - 1) & check(right, right.length(), 0, size/2 -1);
        }
    }
}