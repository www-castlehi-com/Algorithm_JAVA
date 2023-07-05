package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) triangle[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i][j] = triangle[i][j - 1] + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) ans = Math.max(ans, dp[n][i]);

        bw.write(Integer.toString(ans));
        bw.flush();
    }
}
