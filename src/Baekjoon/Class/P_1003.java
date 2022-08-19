package Baekjoon.Class;

import java.io.*;

public class P_1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[] testCase = new int[t];
        int maxCase = Integer.MIN_VALUE;
        for (int i = 0; i < t; i++) {
            testCase[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[41][2];
        dp[0][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= 40; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }

        for (int i : testCase) {
            bw.write(dp[i][0] + " " + dp[i][1] + "\n");
        }
        bw.flush();
    }
}
