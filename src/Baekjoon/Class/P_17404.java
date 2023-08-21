package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_17404 {

    static final int MAX = 1000 * 1000 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] houses = new int[n][n];
        for (int i = 0; i < n; i++) houses[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[1001][3];
        int ans = MAX;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == j) dp[0][j] = houses[0][j];
                else dp[0][j] = MAX;
            }

            for (int j = 1; j < n; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + houses[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + houses[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + houses[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if (i != j) ans = Math.min(ans, dp[n - 1][j]);
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }
}
