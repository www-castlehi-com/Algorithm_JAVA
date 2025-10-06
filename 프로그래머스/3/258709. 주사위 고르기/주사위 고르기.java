import java.util.*;

class Solution {
    
    private boolean[] diceA;
    private int[] answer;
    private int maxWin = 0;
    
    public int[] solution(int[][] dice) {
        diceA = new boolean[dice.length];
        answer = new int[dice.length / 2];
        
        gamePerDice(dice, 0, 0);
        
        return answer;
    }
    
    private void gamePerDice(int[][] dice, int diceACount, int curDice) {
        if (diceACount == dice.length / 2) {
            int[] diceA = getDice(dice.length, true);
            int[] diceB = getDice(dice.length, false);
            
            List<Integer> diceASum = new ArrayList<>();
            rollDice(dice, diceA, diceASum, 0, 0);
            List<Integer> diceBSum = new ArrayList<>();
            rollDice(dice, diceB, diceBSum, 0, 0);
            
            int curWin = game(diceASum, diceBSum);
            if (curWin > maxWin) {
                maxWin = curWin;
                getAnswer(diceA);
            }
        } else {
            for (int i = curDice; i < dice.length; i++) {
                diceA[i] = true;
                gamePerDice(dice, diceACount + 1, i + 1);
                diceA[i] = false;
            }
        }
    }
    
    private int[] getDice(int diceCount, boolean isDiceA) {
        int[] dice = new int[diceCount / 2];
        int index = 0;
        for (int i = 0; i < diceCount; i++) {
            if (diceA[i] == isDiceA) {
                dice[index++] = i;
            }
        }
        
        return dice;
    }
    
    private void rollDice(int[][] dice, int[] target, List<Integer> targetSum, int curSum, int index) {
        if (index == dice.length / 2) {
            targetSum.add(curSum);
        } else {
            for (int i = 0; i < 6; i++) {
                rollDice(dice, target, targetSum, curSum + dice[target[index]][i], index + 1);
            }
        }
    }
    
    private int game(List<Integer> diceA, List<Integer> diceB) {
        int totalWin = 0;
        Collections.sort(diceB);
        for (int dice : diceA) {
            totalWin += getWinningCount(dice, diceB);
        }
        
        return totalWin;
    }
    
    private int getWinningCount(int dice, List<Integer> diceB) {
        int start = 0;
        int end = diceB.size() - 1;
        
        int winningCount = 0;
        while (start <= end) {
            int mid = (start + end) / 2;
            
            if (diceB.get(mid) < dice) {
                start = mid + 1;
                winningCount = Math.max(winningCount, mid);
            } else {
                end = mid - 1;
            }
        }
        
        if (winningCount != 0) {
            winningCount++;
        }
        
        return winningCount;
    }
    
    private void getAnswer(int[] dice) {
        for (int i = 0; i < dice.length; i++) {
            answer[i] = dice[i] + 1;
        }
    }
}