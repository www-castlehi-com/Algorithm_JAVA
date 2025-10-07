import java.util.*;

class Solution {
    
    private static int YEAR = 28 * 12;
    private static int MONTH = 28;
    
    private Map<String, Integer> termDate = new HashMap<>();
    private int todayDate = 0;
    private List<Integer> destroy = new ArrayList<>();
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        initializeTerms(terms);
        initializeTodayDate(today);
        
        check(privacies);
        
        int[] answer = new int[destroy.size()];
        for (int i = 0; i < destroy.size(); i++) {
            answer[i] = destroy.get(i);
        }
        return answer;
    }
    
    private void initializeTerms(String[] terms) {
        for (int i = 0 ; i < terms.length; i++) {
            String[] term = terms[i].split(" ");
            termDate.put(term[0], Integer.valueOf(term[1]) * MONTH);
        }
    }
    
    private void initializeTodayDate(String today) {
        todayDate = convertDate(today.split("\\."));
    }
    
    private void check(String[] privacies) {
        for (int i = 0 ; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            int date = convertDate(privacy[0].split("\\.")) + termDate.get(privacy[1]);
            
            if (todayDate >= date) {
                destroy.add(i + 1);
            }
        }
    }
    
    private int convertDate(String[] date) {
        return Integer.valueOf(date[0]) * YEAR + Integer.valueOf(date[1]) * MONTH + Integer.valueOf(date[2]);
    }
}