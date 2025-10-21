class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = 0;
        
        long left = 0;
        long right = (long)(10e9 * 10e5 * 2 * 2);
        while (left <= right) {
            long mid = (left + right) / 2;
            
            if (canMove(mid, a, b, g, s, w, t)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    public boolean canMove(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long totalGold = 0;
        long totalSilver = 0;
        long totalAll = 0;
        
        for (int i = 0; i < g.length; i++) {
            long moveCount = time / (t[i] * 2);
            if (time % (t[i] * 2) >= t[i]) {
                moveCount++;
            }
            
            long maxCarry = moveCount * w[i];
            
            long gold = Math.min(g[i], maxCarry);
            long silver = Math.min(s[i], maxCarry);
            long all = Math.min(gold + silver, maxCarry);
            
            totalGold += gold;
            totalSilver += silver;
            totalAll += all;
        }
        
        return (totalGold >= a) && (totalSilver >= b) && (totalAll >= (long) a + b);
    }
}