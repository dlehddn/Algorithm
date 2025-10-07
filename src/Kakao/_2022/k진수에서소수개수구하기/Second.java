package Kakao._2022.k진수에서소수개수구하기;

// start 5:14
// end 5:37
// -> 23m 소요
import java.util.*;
class Second {
    public int solution(int n, int k) {
        int answer = 0;
        String kNumber = toKNumber(n, k);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < kNumber.length(); i++) {
            int curNum = kNumber.charAt(i) - '0';
            if (curNum != 0) {
                sb.append(curNum);
                if (i != kNumber.length() - 1) {
                    continue;
                }
            }
            if (sb.length() > 0 && (curNum == 0 || i == kNumber.length() - 1)) {
                if (isPrimeNumber(Long.parseLong(sb.toString()))) {
                    answer++;
                }
                sb = new StringBuilder();
            }
        }
        return answer;
    }

    private boolean isPrimeNumber(long n) {
        if (n == 1) return false;

        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private String toKNumber(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n >= k) {
            sb.append(n % k);
            n /= k;

            if (n < k) {
                sb.append(n);
            }
        }
        sb.reverse();
        return sb.toString();
    }
}
