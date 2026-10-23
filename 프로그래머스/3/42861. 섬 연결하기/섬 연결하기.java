import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        List<int[]>[] costsByIslands = new ArrayList[n + 1];
        for (int i = 0; i < n; i++) {
            costsByIslands[i] = new ArrayList<int[]>();
        }
        
        for (int[] cost: costs) {
            costsByIslands[cost[0]].add(new int[]{cost[1], cost[2]});
            costsByIslands[cost[1]].add(new int[]{cost[0], cost[2]});
        }
        
        Queue<int[]> pq = new PriorityQueue<int[]>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{0, 0});
        int minCost = 0;
        boolean[] visited = new boolean[n + 1];
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (visited[cur[0]]) {
                continue;
            }
            
            minCost += cur[1];
            
            for (int[] costByIsland: costsByIslands[cur[0]]) {
                if (!visited[costByIsland[0]]) {
                    pq.add(new int[]{costByIsland[0], costByIsland[1]});
                }
            }
            
            visited[cur[0]] = true;
        }
        
        return minCost;
    }
}