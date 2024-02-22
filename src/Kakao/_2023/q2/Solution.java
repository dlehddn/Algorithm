package Kakao._2023.q2;

class Solution {
    /**
     * 1. 맨 끝에 있는 배달지 인덱스와 맨 끝에 있는 수거지 인덱스를 기억
     * 2. 맨 끝 배달지 까지 최대 개수를 가지고 배달, 이후 수거지 인덱스가 이전이라면 간 만큼 돌아오면 됨
     * 3. 대신 연산만 수거 처리 해주기
     * 4. 수거지 인덱스가 이후라면 추가로 이동, total 거리로 돌아오기
     */
    static long result;
    static int deliverIdx, pickIdx;

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        pickIdx = -1;
        deliverIdx = -1;

        for (int i = n - 1; i >= 0; i--) {
            if (deliveries[i] != 0) {
                deliverIdx = i;
                break;
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            if (pickups[i] != 0) {
                pickIdx = i;
                break;
            }
        }

        if (pickIdx == -1 && deliverIdx == -1) {
            return 0;
        }
        while (true) {
            if (deliverIdx == -1 && pickIdx == -1) break;
            int tmp = deliverIdx;
            delivery(cap, deliveries);
            pickUp(cap, pickups, tmp);
        }

        return result;
    }

    static void delivery(int cap, int[] deliveries) {
        if (deliverIdx == -1) return;
        result += deliverIdx + 1;
        if (pickIdx == -1) {
            result += deliverIdx + 1;
        }
        while (true) {
            if (deliverIdx == -1) {
                break;
            }
            if (deliveries[deliverIdx] == 0) {
                deliverIdx--;
                continue;
            }
            if (cap == 0) break;
            deliveries[deliverIdx]--;
            cap--;
            if (deliveries[deliverIdx] == 0) {
                deliverIdx--;
            }
        }
    }

    static void pickUp(int cap, int[] pickups, int deliverTmp) {
        if (pickIdx == -1) return;

        if (deliverTmp != -1) {
            if (deliverTmp >= pickIdx) {
                result += deliverTmp + 1;
            } else {
                result += pickIdx - deliverTmp;
                result += pickIdx + 1;
            }
        } else {
            result += 2 * (pickIdx + 1);
        }

        while (true) {
            if (pickIdx == -1) {
                break;
            }
            if (pickups[pickIdx] == 0) {
                pickIdx--;
                continue;
            }
            if (cap == 0) {
                break;
            }
            pickups[pickIdx]--;
            cap--;
            if (pickups[pickIdx] == 0) {
                pickIdx--;
            }
        }
    }
}
