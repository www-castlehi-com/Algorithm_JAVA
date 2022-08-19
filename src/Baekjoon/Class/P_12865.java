package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_12865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = s[0], k = s[1];
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 1; i <= n; i++) {
            s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int w = s[0], v = s[1];

            for (int j = 1; j <= k; j++) {
                if (j - w >= 0) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
                else dp[i][j] = dp[i - 1][j];
            }
        }

        bw.write(Integer.toString(dp[n][k]));
        bw.flush();
    }
}
