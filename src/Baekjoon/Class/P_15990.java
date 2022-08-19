package Baekjoon.Class;

import java.io.*;

public class P_15990 {

    static long[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[] n = new int[t];
        for (int i = 0; i < t; i++) n[i] = Integer.parseInt(br.readLine());
        dp = new long[100001][4];
        dp[1][1] = dp[2][2] = dp[3][1] = dp[3][2] = dp[3][3] = 1;

        find_dp();
        for (int i = 0; i < t; i++) {
            bw.write(Long.toString((dp[n[i]][1] + dp[n[i]][2] + dp[n[i]][3]) % 1000000009) + "\n");
        }
        bw.flush();
    }

    public static void find_dp() {
        for (int i = 4; i < 100001; i++) {
            dp[i][1] = (dp[i - 1][2] + dp[i - 1][3]) % 1000000009;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][3]) % 1000000009;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2]) % 1000000009;
        }
    }
}
