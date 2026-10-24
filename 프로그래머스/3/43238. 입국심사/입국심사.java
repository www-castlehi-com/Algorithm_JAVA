class Solution {
    public long solution(int n, int[] times) {
        long min = Long.MAX_VALUE;
        long max = 0;
        for (int time: times) {
            min = Math.min(min, time);
            max = Math.max(max, (long) time * n);
        }
        
        long answer = max;
        while (min <= max) {
            long mid = (min + max) / 2;
            
            long count = 0;
            for (int time: times) {
                count += (mid / time);
            }
            
            if (count >= n) {
                max = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                min = mid + 1;
            }
        }
        
        return answer;
    }
}