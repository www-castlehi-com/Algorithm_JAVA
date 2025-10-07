class Solution {
    
    private int[] sale = new int[] {10, 20, 30, 40};
    private int[] salePercent;
    private int maxEmoticonPlus = 0;
    private int maxMoney = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        salePercent = new int[emoticons.length];
        
        saleEmoticon(0, users, emoticons);
        
        return new int[]{maxEmoticonPlus, maxMoney};
    }
    
    private void saleEmoticon(int curIndex, int[][] users, int[] emoticons) {
        if (curIndex == emoticons.length) {
            event(users, emoticons);
        } else {
            for (int i = 0; i < 4; i++) {
                salePercent[curIndex] = i;
                saleEmoticon(curIndex + 1, users, emoticons);
            }
        }
    }
    
    private void event(int[][] users, int[] emoticons) {
        int emoticonPlus = 0;
        int totalMoney = 0;
        for (int i = 0; i < users.length; i++) {
            int targetPercent = users[i][0];
            int targetMoney = users[i][1];
            
            int currentMoney = 0;
            for (int j = 0; j < salePercent.length; j++) {
                if (targetPercent <= sale[salePercent[j]]) {
                    currentMoney += emoticons[j] * (100 - sale[salePercent[j]]) / 100;
                }
            }
            
            if (currentMoney >= targetMoney) {
                emoticonPlus++;
            } else {
                totalMoney += currentMoney;
            }
        }
        
        if (emoticonPlus > maxEmoticonPlus) {
            maxEmoticonPlus = emoticonPlus;
            maxMoney = totalMoney;
        } else if (emoticonPlus == maxEmoticonPlus) {
            maxMoney = Math.max(maxMoney, totalMoney);
        }
    }
}