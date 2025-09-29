import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        Arrays.sort(score);
        for (int i = score.length - 1; i - m + 1 >= 0; i -= m) {
            answer += (score[i - m + 1] * m);
        }
        
        return answer;
    }
}