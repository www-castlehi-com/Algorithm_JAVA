import java.io.*;
import java.util.*;

public class Main {
    
    private static final Integer MAX = 1_000_000 * 16 * 16;
    
    private static int[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dp = new int[n][1 << n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        System.out.print(tsp(n, map, 0, 1));
    }
    
    private static int tsp(int n, int[][] map, int cur, int visited) {
        if (visited == (1 << n) - 1) {
            if(map[cur][0] == 0) return MAX;
            return map[cur][0];
        }
        
        if (dp[cur][visited] != -1) {
            return dp[cur][visited];
        }
        
        int minCost = MAX;
        for (int i = 0; i < n; i++) {
            if ((visited & (1 << i)) != 0) {
                continue;
            }
            if (map[cur][i] == 0) {
                continue;
            }
            
            int cost = tsp(n, map, i, visited | (1 << i)) + map[cur][i];
            minCost = Math.min(minCost, cost);
        }
        
        return dp[cur][visited] = minCost;
    }
}