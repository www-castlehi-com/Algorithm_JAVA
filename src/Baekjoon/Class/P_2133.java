package Baekjoon.Class;

import java.io.*;

public class P_2133 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[31];
        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i<= n; i += 2) {
            dp[i] = dp[i - 2] * dp[2];
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += dp[j] * 2;
            }
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
