import java.util.*;

class Solution {
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        
        Queue<int[]> pq = new PriorityQueue<>((j1, j2) -> {
            if (j1[2] != j2[2]) return j1[2] - j2[2];
            if (j1[1] != j2[1]) return j1[1] - j2[1];
            return j1[0] - j2[0];
        });
        
        int idx = 0;
        int curTime = 0;
        int totalTime = 0;
        while (!pq.isEmpty() || idx < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= curTime) {
                pq.add(new int[]{idx, jobs[idx][0], jobs[idx][1]});
                idx++;
            }
            if (pq.isEmpty()) {
                curTime = jobs[idx][0];
                continue;
            }
            
            int[] job = pq.poll();
            
            curTime += job[2];
            totalTime += (curTime - job[1]);
        }
        
        return totalTime / jobs.length;
    }
}