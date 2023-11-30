import java.util.*;
class Solution {

    static int deliIdx, pickIdx, deliTotal, pickTotal;
    static Long move = 0L;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        for (int i = 0; i < deliveries.length; i++) {
            if(deliveries[i] != 0) deliIdx = i;
            deliTotal += deliveries[i];

            if(pickups[i] != 0) pickIdx = i;
            pickTotal += pickups[i];
        }

        // Test //
        System.out.println("deliIdx = " + deliIdx);
        System.out.println("pickIdx = " + pickIdx);
        System.out.println("deliTotal = " + deliTotal);
        System.out.println("pickTotal = " + pickTotal);
        //////////


        while (true) {
            if(deliTotal <= 0 || pickTotal <= 0) break;

            int deliTmp = 0;
            int pickTmp = 0;

            if (pickTotal > 0) {
                if(deliTotal > 0) {
                    if (pickIdx > deliIdx) {
                        move += 2 * (pickIdx - deliIdx);
                    }
                    move += deliIdx;
                } else {
                    move += pickIdx;
                }
                for (int i = pickIdx; i >= 0 && pickTmp < cap && pickups[i] > 0; i--) {
                    pickTotal -= 1;
                    pickups[i] -= 1;
                    pickTmp += 1;
                    pickIdx = i;
                }
            }

            if (deliTotal > 0) {
                move += deliIdx;
                for (int i = deliIdx; i >= 0 && deliTmp < cap && deliveries[i] > 0; i--) {
                    deliTotal -= 1;
                    deliveries[i] -= 1;
                    deliTmp += 1;
                    deliIdx = i;
                }
            }
        }


        return move;
    }


}