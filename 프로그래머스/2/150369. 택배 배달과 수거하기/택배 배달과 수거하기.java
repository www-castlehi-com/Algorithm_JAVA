import java.util.*;

class Solution {
    
    private class Package {
        int index;
        int number;
        
        Package(int index, int number) {
            this.index = index;
            this.number = number;
        }
    }
    
    static Stack<Package> deliveryHome = new Stack<>();
    static Stack<Package> pickupHome = new Stack<>();
        
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        for (int i = 0; i < n; i++) {
            if (deliveries[i] != 0) {
                deliveryHome.push(new Package(i + 1, deliveries[i]));
            }
            if (pickups[i] != 0) {
                pickupHome.push(new Package(i + 1, pickups[i]));
            }
        }
        
        long answer = 0;
        while (!deliveryHome.isEmpty() || !pickupHome.isEmpty()) {
            int maxDeliverDistance = 0;
            int leftDeliverCap = cap;
            while (!deliveryHome.isEmpty() && leftDeliverCap > 0) {
                Package delivery = deliveryHome.pop();
                if (delivery.number - leftDeliverCap > 0) {
                    delivery.number -= leftDeliverCap;
                    leftDeliverCap = 0;
                    deliveryHome.push(delivery);
                } else {
                    leftDeliverCap -= delivery.number;
                }
                
                maxDeliverDistance = Math.max(maxDeliverDistance, delivery.index);
            }
            // answer += maxDeliverDistance;
            
            int maxPickupDistance = 0;
            int leftPickupCap = cap;
            while (!pickupHome.isEmpty() && leftPickupCap > 0) {
                Package pickup = pickupHome.pop();
                if (pickup.number - leftPickupCap > 0) {
                    pickup.number -= leftPickupCap;
                    leftPickupCap = 0;
                    pickupHome.push(pickup);
                } else {
                    leftPickupCap -= pickup.number;
                }
                
                maxPickupDistance = Math.max(maxPickupDistance, pickup.index);
            }
            maxPickupDistance = Math.max(maxPickupDistance, maxDeliverDistance);
            
            answer += (maxPickupDistance * 2);
        }
        
        return answer;
    }
}