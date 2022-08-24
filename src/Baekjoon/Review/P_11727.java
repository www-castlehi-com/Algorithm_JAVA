package Baekjoon.Review;

import java.io.*;

public class P_11727 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 3;
        for (int i = 3; i <= n; i++) dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007;

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
