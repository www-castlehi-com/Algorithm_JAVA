import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] gymClothes = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            gymClothes[i] = 1;
        }
        for (int lostStudent : lost) {
            gymClothes[lostStudent]--;
        }
        for (int reserveStudent : reserve) {
            gymClothes[reserveStudent]++;
        }
        
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (gymClothes[i] >= 2) {
                if (i > 1 && gymClothes[i - 1] == 0) {
                    gymClothes[i - 1]++;
                    gymClothes[i]--;
                    count++;
                } else if (i < n && gymClothes[i + 1] == 0) {
                    gymClothes[i + 1]++;
                    gymClothes[i]--;
                }
            }
            
            if (gymClothes[i] >= 1) {
                count++;
            }
        }
        
        return count;
    }
}