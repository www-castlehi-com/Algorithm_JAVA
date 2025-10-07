import java.util.*;

class Solution {
    
    private int[] powers = new int[]{1, 3, 7, 15, 31, 63};
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            String binary = getBinary(numbers[i]);
            answer[i] = checkRoot(binary) ? 1 : 0;
        }
        
        return answer;
    }
    
    private String getBinary(Long number) {
        int power = checkPower(number);
        String binary = Long.toBinaryString(number);
        int length = binary.length();
        return "0".repeat(power - length) + binary;
    }
    
    private int checkPower(long number) {
        for (int i = 0; i < powers.length; i++) {
            if (Math.pow(2, powers[i]) > number) {
                return powers[i];
            }
        }
        return -1;
    }
    
    private boolean checkRoot(String binary) {
        if (binary.length() == 0) {
            return true;
        }
        
        int mid = binary.length() / 2;
        String leftTree = binary.substring(0, mid);
        String rightTree = binary.substring(mid + 1, binary.length());
        
        if (binary.charAt(mid) == '0') {
            return isChildZero(leftTree) && isChildZero(rightTree);
        }
        
        return checkRoot(leftTree) && checkRoot(rightTree);
    }
    
    private boolean isChildZero(String binary) {
        if (binary.length() == 0) {
            return true;
        }
        
        int mid = binary.length() / 2;
        String leftTree = binary.substring(0, mid);
        String rightTree = binary.substring(mid + 1, binary.length());
        
        if (binary.charAt(mid) == '1') {
            return false;
        }
        
        return isChildZero(leftTree) && isChildZero(rightTree);
    }
}