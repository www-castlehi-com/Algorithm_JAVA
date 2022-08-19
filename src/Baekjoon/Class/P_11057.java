package Baekjoon.Class;

import java.io.*;

public class P_11057 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][10];

        for (int i = 0; i < 10; i++) dp[1][i] = 1;
        for (int i = 2; i<= n; i++) {
            for (int j = 0; j<= 9; j++) {
                for (int k = 0; k <= j; k++) dp[i][j] = (dp[i][j] + dp[i - 1][k]) % 10007;
            }
        }

        int sum = 0;
        for (int i = 0; i< 10; i++) sum = (sum + dp[n][i]) % 10007;

        bw.write(Integer.toString(sum));
        bw.flush();
    }
}
