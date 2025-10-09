class Solution {
    
    private int minAlp = 0;
    private int minCop = 0;
    private int[][] dp;
    
    public int solution(int alp, int cop, int[][] problems) {
        for (int i = 0; i < problems.length; i++) {
            minAlp = Math.max(problems[i][0], minAlp);
            minCop = Math.max(problems[i][1], minCop);
        }
        dp = new int[minAlp + 1][minCop + 1];
        for (int i = 0; i <= minAlp; i++) {
            for (int j = 0; j <= minCop; j++) {
                if (i <= alp && j <= cop) dp[i][j] = 0;
                else dp[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for (int i = 0; i <= minAlp; i++) {
            for (int j = 0; j <= minCop; j++) {
                calculateMinCost(i, j, problems);
            }
        }
        
        return dp[minAlp][minCop];
    }
    
    private void calculateMinCost(int alp, int cop, int[][] problems) {
        study(alp, cop);
        for (int[] problem : problems) {
            solve(alp, cop, problem);
        }
    }
    
    private void study(int alp, int cop) {
        if (cop < minCop) {
            dp[alp][cop + 1] = Math.min(dp[alp][cop + 1], dp[alp][cop] + 1);
        }
        if (alp < minAlp) {
            dp[alp + 1][cop] = Math.min(dp[alp + 1][cop], dp[alp][cop] + 1);
        }
    }
    
    private void solve(int alp, int cop, int[] problem) {
        if (problem[0] > alp || problem[1] > cop) {
            return ;
        }
        
        int targetAlp = alp + problem[2] > minAlp ? minAlp : alp + problem[2];
        int targetCop = cop + problem[3] > minCop ? minCop : cop + problem[3];
        dp[targetAlp][targetCop] = Math.min(dp[targetAlp][targetCop], dp[alp][cop] + problem[4]);
    }
}