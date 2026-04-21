import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> fail = new HashMap<>();
        for (String name : participant) {
            fail.put(name, fail.getOrDefault(name, 0) + 1);
        }
        
        for (String name : completion) {
            fail.put(name, fail.getOrDefault(name, 0) -1);
        }
        
        String answer = "";
        for (Map.Entry<String, Integer> player : fail.entrySet()) {
            if (player.getValue() > 0) {
                answer = player.getKey();
                break;
            }
        }
        
        return answer;
    }
}