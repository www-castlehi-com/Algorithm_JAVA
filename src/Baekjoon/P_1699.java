package Baekjoon;

import java.io.*;

public class P_1699 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        int cnt = 0;

        for (int i = 1; i<= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = (dp[i] < dp[i - j * j] + 1) ? dp[i] : dp[i - j * j] + 1;
            }
        }
        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
