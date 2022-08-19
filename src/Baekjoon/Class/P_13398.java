package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

class P_13398 {
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());
    int[] seq = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    int[][] dp = new int[n][2];
    dp[0][0] = seq[0]; 
    int max = seq[0];
    
    for(int i = 1; i < n; i++) {
      dp[i][0] = Math.max(dp[i - 1][0] + seq[i], seq[i]);
      dp[i][1] = Math.max(dp[i - 1][1] + seq[i], dp[i - 1][0]);

      int dp_max = Math.max(dp[i][0], dp[i][1]);
      max = (max > dp_max ? max : dp_max);
    }

    bw.write(Integer.toString(max));
    bw.flush();
  }
}
