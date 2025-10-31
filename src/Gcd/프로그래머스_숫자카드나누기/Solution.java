package Gcd.프로그래머스_숫자카드나누기;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        for (int i = 1; i < arrayA.length; i++) {
            gcdA = gcd(gcdA, arrayA[i]);
        }
        for (int i = 1; i < arrayB.length; i++) {
            gcdB = gcd(gcdB, arrayB[i]);
        }
        if (gcdA == 1 && gcdB == 1) {
            return 0;
        }
        int answer = 0;
        if (gcdA != 1) {
            boolean flag = true;
            for (int b: arrayB) {
                if (b % gcdA == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer = Math.max(answer, gcdA);
            }
        }
        if (gcdB != 1) {
            boolean flag = true;
            for (int a: arrayA) {
                if (a % gcdB == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                answer = Math.max(answer, gcdB);
            }
        }
        return answer;
    }

    private int gcd(int a, int b) {
        if (a % b == 0)  {
            return b;
        }

        return gcd(b, a % b);
    }
}
