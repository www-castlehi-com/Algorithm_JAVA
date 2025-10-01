import java.util.*;

class Solution {
    
    private Map<String, Integer> friendsIndex = new HashMap<>();
    private int[][] giftGraph;
    private int[][] giftLevel;
    private int maxReceiveGift = 0;
    
    public int solution(String[] friends, String[] gifts) {
        initialize(friends, gifts);
        
        exchangeGifts();
        
        return maxReceiveGift;
    }
    
    private void initialize(String[] friends, String[] gifts) {
        initializeIndex(friends);
        initializeGiftInformation(gifts);
    }
    
    private void initializeIndex(String[] friends) {
        for (int i = 0; i < friends.length; i++) {
            friendsIndex.put(friends[i], i);
        }
    }
    
    private void initializeGiftInformation(String[] gifts) {
        giftGraph = new int[friendsIndex.size()][friendsIndex.size()];
        giftLevel = new int[friendsIndex.size()][3];
        
        for (String gift : gifts) {
            String[] giftInfo = gift.split(" ");
            String sender = giftInfo[0];
            String receiver = giftInfo[1];
            
            giftGraph[friendsIndex.get(sender)][friendsIndex.get(receiver)]++;
            giftLevel[friendsIndex.get(sender)][0]++;
            giftLevel[friendsIndex.get(receiver)][1]++;
        }
        
        for (int i = 0; i < friendsIndex.size(); i++) {
            giftLevel[i][2] = giftLevel[i][0] - giftLevel[i][1];
        }
    }
    
    private void exchangeGifts() {
        maxReceiveGift = 0;
        
        for (int i = 0; i < friendsIndex.size(); i++) {
            int receiveGift = 0;
            for (int j = 0; j < friendsIndex.size(); j++) {
                if (i == j) {
                    continue;
                }
                
                if (giftGraph[i][j] > giftGraph[j][i]) {
                    receiveGift++;
                } else if (giftGraph[i][j] == giftGraph[j][i] && giftLevel[i][2] > giftLevel[j][2]) {
                    receiveGift++;
                }
            }
            
            maxReceiveGift = Math.max(maxReceiveGift, receiveGift);
        }
    }
}