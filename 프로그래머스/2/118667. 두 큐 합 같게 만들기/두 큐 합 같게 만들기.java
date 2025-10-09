import java.util.*;

class Solution {
    
    private LinkedList<Integer> a = new LinkedList<>();
    private LinkedList<Integer> b = new LinkedList<>();
    private int totalLength = 0;
    private long aSum = 0;
    private long bSum = 0;
    
    public int solution(int[] queue1, int[] queue2) {
        initializeQueue(queue1, queue2);
        
        int count = 0;
        while (aSum != bSum) {
            if (count > 2 * totalLength) {
                return -1;
            }
            
            if (aSum > bSum) {
                int target = a.removeFirst();
                aSum -= target;
                bSum += target;
                b.addLast(target);
            } else {
                int target = b.removeFirst();
                aSum += target;
                bSum -= target;
                a.addLast(target);
            }
            count++;
        }
        
        return count;
    }
    
    private void initializeQueue(int[] queue1, int[] queue2) {
        totalLength = queue1.length * 2;
        for (int i = 0; i < queue1.length; i++) {
            a.add(queue1[i]);
            aSum += queue1[i];
            b.add(queue2[i]);
            bSum += queue2[i];
        }
    }
}