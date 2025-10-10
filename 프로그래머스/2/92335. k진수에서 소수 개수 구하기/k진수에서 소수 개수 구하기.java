import java.util.*;

class Solution {
    
    public int solution(int n, int k) {
        String[] convertedNumbers = getConvertedNumber(n, k);
        
        return getPrimeCount(convertedNumbers);
    }
    
    private String[] getConvertedNumber(int n, int k) {
        String convertNumber = Integer.toString(n, k);
        return convertNumber.split("0");
    }
    
    private int getPrimeCount(String[] convertedNumbers) {
        int count = 0;
        for (String convertedNumber : convertedNumbers) {
            if (convertedNumber.isEmpty()) {
                continue;
            }
            
            long number = Long.valueOf(convertedNumber);
            if (isPrime(number)) {
                count++;
            }
        }
        
        return count;
    }
    
    private boolean isPrime(long n) {
        if (n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }
        
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}