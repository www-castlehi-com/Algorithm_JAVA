import java.util.*;

class Solution {
    
    private static final Integer MAX_INTENSITY = 100_000_000;
    
    private class Mountain implements Comparable<Mountain> {
        int idx;
        int intensity;
        
        Mountain(int idx, int intensity) {
            this.idx = idx;
            this.intensity = intensity;
        }
        
        @Override
        public int compareTo(Mountain mountain) {
            return this.intensity - mountain.intensity;
        }
    }
    
    private List<int[]>[] mountains;
    private int[] intensity;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        initializeMountains(n, paths);
        initializeIntensity(n);
        
        makeIntensity(gates, summits);
        
        return getAnswer(summits);
    }
    
    private void initializeMountains(int n, int[][] paths) {
        mountains = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            mountains[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < paths.length; i++) {
            mountains[paths[i][0]].add(new int[]{paths[i][1], paths[i][2]});
            mountains[paths[i][1]].add(new int[]{paths[i][0], paths[i][2]});
        }
    }
    
    private void initializeIntensity(int n) {
        intensity = new int[n + 1];
        Arrays.fill(intensity, MAX_INTENSITY);
    }
    
    private void makeIntensity(int[] gates, int[] summits) {
        PriorityQueue<Mountain> pq = new PriorityQueue<>();
        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new Mountain(gate, 0));
        }
        
        while (!pq.isEmpty()) {
            Mountain cur = pq.poll();
            
            if ((cur.intensity > intensity[cur.idx]) || isSummit(cur.idx, summits)) {
                continue;
            }
            
            for (int[] mountain : mountains[cur.idx]) {
                int nextIdx = mountain[0];
                int cost = mountain[1];
                
                if (isGate(nextIdx, gates)) {
                    continue;
                }
                
                int newIntensity = Math.max(cur.intensity, cost);
                
                if (newIntensity < intensity[nextIdx]) {
                    intensity[nextIdx] = newIntensity;
                    pq.offer(new Mountain(nextIdx, newIntensity));
                }
            }
        }
    }
    
    private boolean isSummit(int idx, int[] summits) {
        for (int summit : summits) {
            if (summit == idx) {
                return true;
            }
        }
        return false;
    }
    
    private boolean isGate(int idx, int[] gates) {
        for (int gate: gates) {
            if (gate == idx) {
                return true;
            }
        }
        return false;
    }
    
    private int[] getAnswer(int[] summits) {
        Arrays.sort(summits);
        int minSummit = 0;
        int minIntensity = MAX_INTENSITY;
        for (int summit : summits) {
            if (minIntensity > intensity[summit]) {
                minIntensity = intensity[summit];
                minSummit = summit;
            }
        }
        
        return new int[]{minSummit, minIntensity};
    }
}