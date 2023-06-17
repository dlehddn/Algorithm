package Greedy.프로그래머스Lv2마법의엘리베이터;

class Solution {
    private int magicRock = 0; // 멤버변수

    private int[] intToArrays(int storey) {
        String temp = Integer.toString(storey);
        int[] digits = new int[temp.length()];
        for (int i = 0; i < temp.length(); i++)
            digits[i] = temp.charAt(i) - '0';
        return digits;
    }

    private void checkDigitIsTen(int[] digits, int i) {
        if(digits[i] != 10) {
            if(digits[i] > 5) {
                this.magicRock += 10 - digits[i];
                digits[i - 1] += 1;
            }
            else if(digits[i] < 5) this.magicRock += digits[i];
            else {
                if(digits[i - 1] >= 5) {
                    this.magicRock += 10 - digits[i];
                    digits[i - 1] += 1;
                } else this.magicRock += digits[i];
            }
        } else {
            digits[i] = 0;
            digits[i - 1] += 1;
        }
    }

    public int solution(int storey) {
        int[] digits = intToArrays(storey);
        for(int i = digits.length - 1; i >= 0 ; i--) {
            if(i != 0) checkDigitIsTen(digits, i);
            else {
                if(digits[0] > 5) this.magicRock += 10 - digits[0] + 1;
                else this.magicRock += digits[0];
            }
        }
        return this.magicRock;
    }
}