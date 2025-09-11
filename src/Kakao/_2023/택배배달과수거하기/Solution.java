package Kakao._2023.택배배달과수거하기;

//4:54 시작
//5:28 종료
// -> 34m 소요
import java.util.*;

class Solution {
    private PriorityQueue<Box> dQ = new PriorityQueue<>((b1, b2) -> b2.idx - b1.idx);
    private PriorityQueue<Box> pQ = new PriorityQueue<>((b1, b2) -> b2.idx - b1.idx);

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        for(int i = 0; i < n; i++) {
            if (deliveries[i] != 0) {
                dQ.add(new Box(i + 1, deliveries[i]));
            }
            if (pickups[i] != 0) {
                pQ.add(new Box(i + 1, pickups[i]));
            }
        }

        int remains = cap;
        while (!dQ.isEmpty() || !pQ.isEmpty()) {
            remains = cap;
            int totalGo = 0;
            while(!dQ.isEmpty() && remains > 0) {
                Box poll = dQ.poll();
                totalGo = Math.max(totalGo, poll.idx);
                if (poll.remains <= remains) {
                    remains -= poll.remains;
                } else {
                    dQ.add(new Box(poll.idx, poll.remains - remains));
                    remains = 0;
                }
            }

            remains = cap;
            while(!pQ.isEmpty() && remains > 0) {
                Box poll = pQ.poll();
                totalGo = Math.max(totalGo, poll.idx);
                if (poll.remains <= remains) {
                    remains -= poll.remains;
                } else {
                    pQ.add(new Box(poll.idx, poll.remains - remains));
                    remains = 0;
                }
            }
            answer += 2 * totalGo;
        }
        return answer;
    }

    static class Box {
        int idx;
        int remains;

        public Box(int idx, int remains) {
            this.idx = idx;
            this.remains = remains;
        }
    }
}