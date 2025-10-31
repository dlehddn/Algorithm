package Heap.프로그래머스_디펜스게임;

// n <= 10억
// k <= 500,000
// 라운드 <= 1,000,000
import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < enemy.length; i++) {
            int curEnemy = enemy[i];
            if (k > 0) {
                pq.add(curEnemy);
                k--;
            } else {
                int min = pq.poll();
                if (min < curEnemy) {
                    pq.add(curEnemy);
                    n -= min;
                } else {
                    pq.add(min);
                    n -= curEnemy;
                }
                if (n < 0) return i;
            }
        }
        return enemy.length;
    }
}