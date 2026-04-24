class Solution {
    
    private static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        findTarget(0, numbers, 0, target);
        return answer;
    }
    
    private void findTarget(int idx, int[] numbers, int sum, int target) {
        if (idx == numbers.length) {
            if (sum == target) {
                answer++;
            }
        } else {
            findTarget(idx + 1, numbers, sum + numbers[idx], target);
            findTarget(idx + 1, numbers, sum - numbers[idx], target);
        }
    }
}