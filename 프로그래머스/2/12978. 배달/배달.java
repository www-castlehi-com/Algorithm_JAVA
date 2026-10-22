import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        List<int[]>[] info = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            info[i] = new ArrayList<int[]>();
        }
        for (int i = 0; i < road.length; i++) {
            info[road[i][0]].add(new int[]{road[i][1], road[i][2]});
            info[road[i][1]].add(new int[]{road[i][0], road[i][2]});
        }
        
        int[] shortest = new int[N + 1];
        Arrays.fill(shortest, Integer.MAX_VALUE);
        shortest[1] = 0;
        
        Queue<Integer> targets = new LinkedList<>();
        targets.add(1);
        while (!targets.isEmpty()) {
            int curTarget = targets.poll();
            
            for (int[] curInfo: info[curTarget]) {
                int nextTarget = curInfo[0];
                int time = curInfo[1];
                
                int curTime = shortest[nextTarget];
                int newTime = shortest[curTarget] + time;
                if (newTime < curTime) {
                    shortest[nextTarget] = newTime;
                    targets.add(nextTarget);
                }
            }
        }
        
        int count = 0;
        for (int time: shortest) {
            if (time <= K) {
                count ++;
            }
        }

        return count;
    }
}