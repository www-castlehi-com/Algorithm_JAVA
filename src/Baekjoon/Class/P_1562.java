package Baekjoon.Class;

import java.io.*;

public class P_1562 {

    static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][][] dp = new int[n + 1][10][1024];
        for (int i = 1; i <= 9; i++) dp[1][i][1 << i] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j);
                    if (j == 0) dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j + 1][k]) % MOD;
                    else if (j == 9) dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k]) % MOD;
                    else dp[i][j][bit] = (dp[i][j][bit] + dp[i - 1][j - 1][k] + dp[i - 1][j + 1][k]) % MOD;
                }
            }
        }

        long ans = 0L;
        for (int i = 0; i <= 9; i++) {
            ans = (ans + dp[n][i][1023]) % MOD;
        }
        bw.write(Long.toString(ans));
        bw.flush();
    }
}
