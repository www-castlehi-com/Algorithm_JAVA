import java.util.*;

class Solution {
    
    private List<Integer> haveCards = new ArrayList<>();
    private List<Integer> nonHaveCards = new ArrayList<>();
    private int curCoin;
    
    public int solution(int coin, int[] cards) {
        curCoin = coin;
        
        getInitialCards(cards);
        
        return game(cards);
    }
    
    private void getInitialCards(int[] cards) {
        for (int i = 0; i < cards.length / 3; i++) {
            haveCards.add(cards[i]);
        }
    }
    
    private int game(int[] cards) {
        int sum = cards.length + 1;
        int count = 0;
        int index = 0;
        for (index = cards.length / 3; index < cards.length; index += 2) {
            count++;
            nonHaveCards.add(cards[index]);
            nonHaveCards.add(cards[index + 1]);
            
            boolean canMake = checkInHaveCards(sum);
            
            if (!canMake && curCoin >= 1) {
                canMake = checkWithHaveCards(sum);
            }
            
            if (!canMake && curCoin >= 2) {
                canMake = checkWithAvailableCards(sum);
            }
            
            if (!canMake) {
                break;
            }
        }
        
        if (index == cards.length) {
            count++;
        }
        
        return count;
    }
    
    private boolean checkInHaveCards(int sum) {
        for (int i = 0 ; i < haveCards.size(); i++) {
            int target = haveCards.get(i);
            if (haveCards.contains(sum - target)) {
                haveCards.remove((Object) (sum - target));
                haveCards.remove(i);
                
                return true;
            }
        }

        return false;
    }
    
    private boolean checkWithHaveCards(int sum) {
        for (int i = 0; i < haveCards.size(); i++) {
            int target = haveCards.get(i);
            
            if (nonHaveCards.contains(sum - target)) {
                haveCards.remove(i);
                nonHaveCards.remove((Object)(sum - target));
                curCoin--;
                
                return true;
            }
        }
        
        return false;
    }
    
    private boolean checkWithAvailableCards(int sum) {        
        for (int i = 0; i < nonHaveCards.size(); i++) {
            int target = nonHaveCards.get(i);
            if (nonHaveCards.contains(sum - target)) {
                nonHaveCards.remove(i);
                nonHaveCards.remove((Object) (sum - target));
                curCoin -= 2;
                
                return true;
            }
        }
        
        return false;
    }
}