package StringAndArray.프로그래머스Lv2_124나라의숫자;

class Solution {
    public String solution(int n) {
        String num_124 = "";
        while(n > 0) {
            if(n % 3 == 0) {
                num_124 += "4";
                n = n/3 - 1;
            }
            if(n % 3 == 1) {
                num_124 += "1";
                n = n/3;
            }
            if(n % 3 == 2) {
                num_124 += "2";
                n = n/3;
            }
        }
        StringBuffer sb = new StringBuffer(num_124);
        String reverse = sb.reverse().toString();
        return reverse;
    }

}