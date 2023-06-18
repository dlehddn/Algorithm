package StringAndArray.프로그래머스Lv2_문자열압축;

class Solution {

    public String makeCompressed(String s, int n) {              // 문자열 압축 메소드
        StringBuilder sb = new StringBuilder();
        int count = 1;                                           // 중복 카운트
        int num = s.length() / n;                                // n개 단위로 잘랐을 때 몇개인지
        for(int i = 0; i < num * n - n; i += n) {
            if(s.substring(i, i+n).equals(s.substring(i+n, i+2*n))){
                count++;                                         // 중복이면 count++
            }
            else if(count == 1){                                 // 이전에 중복이 없으면 단위문자열 그대로 append
                sb.append(s.substring(i, i+n));
            }
            else {
                sb.append(count).append(s.substring(i, i+n));    // 중복이 있었으면 count먼저 append하고
                count = 1;                                       // 이후 단위문자열 append
            }
        }                                                        // 여기까지만 하면 맨 마지막이 append 동작을 안함

        int remainder = s.length() % n;                          // 주어진 문자열을 단위길이로 나누었을 때 남는게 있는지
        if(count != 1) {
            sb.append(count).append(s.substring(s.length() - n - remainder));
        }
        else sb.append(s.substring(s.length() - n - remainder)); // 마지막 단위 문자열을 넣어주기
        return sb.toString();
    }

    public int solution(String s) {
        int answer = makeCompressed(s,1).length();               // 문자열을 1개 단위로 잘랐을 때 result
        int temp;
        for(int i = 2; i <= s.length() / 2; i++) {               // 이후 2개 단위로 잘랐을 경우부터 끝까지 확인
            temp = makeCompressed(s,i).length();
            if(temp < answer) answer = temp;                     // 비교한 값이 더 작다면 answer로 대입
        }
        return answer;
    }
}
