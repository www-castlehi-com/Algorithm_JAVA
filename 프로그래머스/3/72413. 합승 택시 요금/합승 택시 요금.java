import java.util.*;

class Solution {
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        List<int[]>[] faresByPoint = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            faresByPoint[i] = new ArrayList<int[]>();
        }
        for (int[] fare: fares) {
            faresByPoint[fare[0]].add(new int[]{fare[1], fare[2]});
            faresByPoint[fare[1]].add(new int[]{fare[0], fare[2]});
        }
        
        int[][] minFares = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(minFares[i], Integer.MAX_VALUE);
        }
        for (int i = 1; i <= n; i++) {
            calculateMinFares(i, minFares[i], faresByPoint);
        }
        
        return calculateResult(n, s, a, b, minFares);
    }
    
    private void calculateMinFares(int point, int[] minFare, List<int[]>[] faresByPoint) {
        Queue<int[]> pq = new PriorityQueue<>((p1, p2) -> p1[1] - p2[1]);
        pq.add(new int[]{point, 0});
        minFare[point] = 0;
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            
            if (minFare[cur[0]] < cur[1]) {
                continue ;
            }
            
            for (int[] nextFaresByPoint: faresByPoint[cur[0]]) {
                int fare = cur[1] + nextFaresByPoint[1];
                
                if (minFare[nextFaresByPoint[0]] <= fare) {
                    continue;
                }
                
                minFare[nextFaresByPoint[0]] = fare;
                pq.add(new int[]{nextFaresByPoint[0], fare});
            }
        }
    }
    
    private int calculateResult(int n, int s, int a, int b, int[][] minFares) {
        int result = minFares[s][a] + minFares[s][b];
        for (int i = 1; i <= n; i++) {
            if (minFares[s][i] == Integer.MAX_VALUE || minFares[i][a] == Integer.MAX_VALUE || minFares[i][b] == Integer.MAX_VALUE) {
                continue;
            }
            
            result = Math.min(result, minFares[s][i] + minFares[i][a] + minFares[i][b]);
        }
        
        return result;
    }
}