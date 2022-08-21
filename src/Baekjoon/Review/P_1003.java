package Baekjoon.Review;

import java.io.*;

public class P_1003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[][] dp = new int[41][2];
        dp[0][0] = 1; dp[1][1] = 1;
        for (int i = 2; i < 41; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 2][0];
            dp[i][1] = dp[i - 1][1] + dp[i - 2][1];
        }

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            bw.write(dp[n][0] + " " + dp[n][1] + "\n");
        }
        bw.flush();
    }
}
