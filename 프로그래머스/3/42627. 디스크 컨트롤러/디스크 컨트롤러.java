import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        int index = 0;
        int count = 0;
        int curTime = 0;
        int resultTime = 0;
        while (count < jobs.length) {
            while (index < jobs.length && jobs[index][0] <= curTime) {
                pq.add(jobs[index++]);
            }
            
            if (pq.isEmpty()) {
                curTime = jobs[index][0];
            } else {
                int[] request = pq.poll();
                
                curTime += request[1];
                resultTime += (curTime - request[0]);
                count++;
            }
        }
        
        return resultTime / jobs.length;
    }
}