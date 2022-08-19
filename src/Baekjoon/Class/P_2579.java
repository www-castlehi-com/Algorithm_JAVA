package Baekjoon.Class;

import java.io.*;

public class P_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n + 1];
        for (int i = 1; i <= n; i++) stairs[i] = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];

        dp[1] = stairs[1];
        if (n >= 2) {
            dp[2] = stairs[1] + stairs[2];
            for (int i = 3; i <= n; i++) {
                dp[i] = Math.max(dp[i - 2] + stairs[i], dp[i - 3] + stairs[i - 1] + stairs[i]);
            }
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
