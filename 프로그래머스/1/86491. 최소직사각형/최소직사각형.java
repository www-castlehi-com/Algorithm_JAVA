import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int size = 0;
        int width = 0;
        for (int i = 0; i < sizes.length; i++) {
            int curWidth = Math.max(sizes[i][0], sizes[i][1]);
            width = Math.max(curWidth, width);
        }
        for (int i = 0; i < sizes.length; i++) {
            int length = Math.min(sizes[i][0], sizes[i][1]);
            
            size = Math.max(size, length * width);
        }
        return size;
    }
}