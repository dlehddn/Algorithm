package Gcd.프로그래머스_멀쩡한사각형;

import java.util.*;
// 최대 공약수 찾기 -> a
// 최대 공약수로 각 수 나누기 -> x, y
// 이 조그만 블럭에서 사용 못하는 개수는 x + (y - 1)
// -> 총 사용 못하는 개수는 a * (x + y - 1)
// w * h에서 빼면 정답
class Solution {
    public long solution(int w, int h) {
        long answer = 1;
        int gcd = gcd(w, h);
        int dividedW = w / gcd;
        int dividedH = h / gcd;
        return (long) w * h - (long) (dividedW + dividedH - 1) * gcd;
    }

    private int gcd(int y, int x) {
        if (y % x == 0) return x;

        return gcd(x, y % x);
    }
}
