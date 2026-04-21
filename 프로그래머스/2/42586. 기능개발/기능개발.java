import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> leftDays = new ArrayList<>();
        for (int i = 0; i < progresses.length; i++) {
            int leftProgress = 100 - progresses[i];
            int leftDay = (leftProgress / speeds[i]) + (leftProgress % speeds[i] == 0 ? 0 : 1);
            leftDays.add(leftDay);
        }
        
        List<Integer> answer = new ArrayList<>();
        int prevLeftDay = leftDays.get(0);
        int prevCompleteCount = 1;
        for (int i = 1; i < leftDays.size(); i++) {
            if (leftDays.get(i) <= prevLeftDay) {
                prevCompleteCount++;
            } else {
                answer.add(prevCompleteCount);
                prevLeftDay = leftDays.get(i);
                prevCompleteCount = 1;
            }
        }
        answer.add(prevCompleteCount);
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}