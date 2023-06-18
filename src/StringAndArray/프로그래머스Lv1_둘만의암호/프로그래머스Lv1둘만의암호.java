package StringAndArray.프로그래머스Lv1_둘만의암호;

class Solution {
    public String solution(String s, String skip, int index) {
        String result = "";
        for(int i = 0; i < s.length(); i++) {
            char charac = s.charAt(i);
            for(int j = 0; j < index; j++) {
                int num = (int) charac;
                num += 1;
                if(num > 122) num -= 26;

                charac = (char) num;
                if(skip.contains(Character.toString(charac))) j--;
            }
            result += charac;
        }
        return result;
    }
}