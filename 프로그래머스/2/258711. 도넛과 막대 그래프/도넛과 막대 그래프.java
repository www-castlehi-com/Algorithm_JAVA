import java.util.*;

class Solution {
    
    private static final Integer MAX_VERTEX = 1_000_000;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int[] entryCount = new int[MAX_VERTEX + 1];
        int[] exitCount = new int[MAX_VERTEX + 1];
        
        for (int i = 0; i < edges.length; i++) {
            exitCount[edges[i][0]]++;
            entryCount[edges[i][1]]++;
        }
        
        for (int i = 1; i <= MAX_VERTEX; i++) {
            if (exitCount[i] >= 2) {
                if (entryCount[i] == 0) {
                    answer[0] = i;
                } else {
                    answer[3]++;
                }
            } else if (exitCount[i] == 0 && entryCount[i] > 0) {
                answer[2]++;
            }
        }
        
        answer[1] = exitCount[answer[0]] - answer[2] - answer[3];
        
        return answer;
    }
}