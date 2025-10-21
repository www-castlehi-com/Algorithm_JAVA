class Solution {
    
    public int solution(int[][] triangle) {    
        for (int i = 1; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                int left = 0;
                int right = 0;
                if (j <= i - 1) {
                    right = triangle[i - 1][j];
                }
                if (j - 1 >= 0) {
                    left = triangle[i - 1][j - 1];
                }
                triangle[i][j] = triangle[i][j] + Math.max(left, right);
            }
        }
        
        int answer = 0;
        for (int i = 0; i < triangle[triangle.length - 1].length; i++) {
            answer = Math.max(answer, triangle[triangle.length - 1][i]);
        }
        
        return answer;
    }
}