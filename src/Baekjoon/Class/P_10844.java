package Baekjoon.Class;

import java.io.*;

public class P_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[101][10];
        for (int i = 1; i < 10; i++) dp[1][i] = 1;

        for (int i = 2; i < 101; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0) dp[i][j] = (dp[i - 1][j + 1]) % 1000000000;
                else if (j == 9) dp[i][j] = (dp[i - 1][j - 1]) % 1000000000;
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % 1000000000;
            }
        }

        long sum = 0;
        for (long num : dp[n]) sum += num;
        bw.write(Long.toString(sum % 1000000000));
        bw.flush();
    }
}

