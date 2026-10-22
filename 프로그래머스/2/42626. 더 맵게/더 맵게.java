import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        Queue<Integer> foods = new PriorityQueue<>();
        for (int scovillePoint: scoville) {
            foods.add(scovillePoint);
        }

        int count = 0;
        while (foods.peek() < K) {
            if (foods.size() == 1) {
                return -1;
            }
            
            int point = foods.poll() + foods.poll() * 2;
            foods.add(point);
            count++;
        }
        
        return count;
    }
}