import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] knapsack = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            knapsack[i] = new int[]{w, v};
        }
        
        int[] dp = new int[k + 1];
        for (int i = 0; i < n; i++) {
            for (int j = k; j >= knapsack[i][0]; j--) {
                dp[j] = Math.max(dp[j], dp[j - knapsack[i][0]] + knapsack[i][1]);
            }
        }
        
        System.out.print(dp[k]);
    }
}