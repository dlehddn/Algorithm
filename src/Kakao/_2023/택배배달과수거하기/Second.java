package Kakao._2023.택배배달과수거하기;

// start 11:57
// end 12:13
// 16m 소요
import java.util.*;
class Second {
    PriorityQueue<House> deliveryQ = new PriorityQueue<>((h1, h2) -> h2.idx - h1.idx);
    PriorityQueue<House> pickupQ= new PriorityQueue<>((h1, h2) -> h2.idx - h1.idx);

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        for (int i = 0; i < deliveries.length; i++) {
            if (deliveries[i] != 0) {
                deliveryQ.add(new House(i + 1, deliveries[i]));
            }
            if (pickups[i] != 0) {
                pickupQ.add(new House(i + 1, pickups[i]));
            }
        }
        while (!deliveryQ.isEmpty() || !pickupQ.isEmpty()) {
            int maxDistance = 0;

            int remainDelivery = cap;
            while (!deliveryQ.isEmpty() && remainDelivery > 0) {
                House house = deliveryQ.poll();
                maxDistance = Math.max(house.idx, maxDistance);
                if (remainDelivery >= house.amount) {
                    remainDelivery -= house.amount;
                } else {
                    deliveryQ.add(new House(house.idx, house.amount - remainDelivery));
                    remainDelivery = 0;
                }
            }

            int remainPickup = cap;
            while (!pickupQ.isEmpty() && remainPickup > 0) {
                House house = pickupQ.poll();
                maxDistance = Math.max(house.idx, maxDistance);
                if (remainPickup >= house.amount) {
                    remainPickup -= house.amount;
                } else {
                    pickupQ.add(new House(house.idx, house.amount - remainPickup));
                    remainPickup = 0;
                }
            }
            answer += maxDistance * 2;
        }
        return answer;
    }

    static class House {
        int idx, amount;

        public House(int idx, int amount) {
            this.idx = idx;
            this.amount = amount;
        }
    }
}
