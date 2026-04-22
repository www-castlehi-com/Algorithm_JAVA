import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> mixedScovilles = new PriorityQueue<>();
        for (int food : scoville) {
            mixedScovilles.add((long)food);
        }
        
        int count = 0;
        while (mixedScovilles.size() >= 2 && mixedScovilles.peek() < K) {
            long scoville1 = mixedScovilles.poll();
            long scoville2 = mixedScovilles.poll();
            
            long result = (scoville1 + (scoville2 * 2));
            
            mixedScovilles.add(result);
            
            count++;
        }
        
        return (mixedScovilles.peek() < K) ? -1 : count;
    }
}