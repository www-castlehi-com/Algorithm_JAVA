import java.util.*;

class Solution {
    
    private class Disk {
        int idx;
        int requestTime;
        int durationTime;
        
        public Disk(int idx, int requestTime, int durationTime) {
            this.idx = idx;
            this.requestTime = requestTime;
            this.durationTime = durationTime;
        }
    }
    
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (j1, j2) -> j1[0] - j2[0]);
        PriorityQueue<Disk> diskController = new PriorityQueue<>((d1, d2) -> {
            if (d1.durationTime != d2.durationTime) {
                return d1.durationTime - d2.durationTime;
            }
            
            if (d1.requestTime != d2.requestTime) {
                return d1.requestTime - d2.requestTime;
            }
            
            return d1.idx - d2.idx;
        });
        
        int endCount = 0;
        int idx = 0;
        int curTime = 0;
        int elapsedTime = 0;
        while(endCount < jobs.length) {
            while (idx < jobs.length && jobs[idx][0] <= curTime) {
                diskController.add(new Disk(idx, jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if (diskController.isEmpty()) {
                curTime = jobs[idx][0];
            } else {
                Disk disk = diskController.poll();
            
                curTime += disk.durationTime;
                elapsedTime += (curTime - disk.requestTime);
                
                endCount++;
            }
        }
        
        return elapsedTime / jobs.length;
    }
}