package Hash.프로그래머스Lv1_체육복;
import java.util.Set;
import java.util.HashSet;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int count = 0;
        Set<Integer> set_lost = new HashSet<>();
        Set<Integer> set_reserve = new HashSet<>();
        for(int res : reserve) {
            set_reserve.add(res);
        }
        for(int los : lost) {
            set_lost.add(los);
        }   // HashSet 각각 선언
        for(int i = 1; i <=n; i++) {
            if(set_lost.contains(i) && set_reserve.contains(i)) {
                set_lost.remove(i);
                set_reserve.remove(i);
            }
        }   // 도둑맞았는데 동시에 여분있는 학생은 타인에게 빌려줄 수 없음
        for(int i = 1; i <= n; i++) {
            if(!set_lost.contains(i)) count++;
            else if(set_reserve.contains(i - 1)) count++;
            else if(set_reserve.contains(i + 1)) {
                count++;
                set_reserve.remove(i + 1);
            }
        }   // 내 앞번호 친구한테 빌리는게 우선. 앞번호 친구가 없으면 뒷번호 친구한테 빌리기
        return count;
    }
}