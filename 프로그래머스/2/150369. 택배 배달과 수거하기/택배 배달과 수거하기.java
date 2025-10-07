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
            int maxDistance = 0;
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
                
                maxDistance = Math.max(maxDistance, delivery.index);
            }
            
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
                
                maxDistance = Math.max(maxDistance, pickup.index);
            }
            
            answer += (maxDistance * 2);
        }
        
        return answer;
    }
}