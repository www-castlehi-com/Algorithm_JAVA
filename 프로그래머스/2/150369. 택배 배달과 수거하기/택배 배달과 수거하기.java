class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        int deliver = 0;
        int pickup = 0;
        long answer = 0;
        
        for (int i = n - 1; i >= 0; i--) {
            deliver += deliveries[i];
            pickup += pickups[i];
            
            while (deliver > 0 || pickup > 0) {
                deliver -= cap;
                pickup -= cap;
                answer += ((i + 1) * 2);
            }
        }
        
        return answer;
    }
}