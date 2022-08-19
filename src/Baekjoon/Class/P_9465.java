package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[][] stickers = new int[2][n];
            for (int i = 0; i < 2; i++) stickers[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int result = Math.max(stickers[0][0], stickers[1][0]);
            int[][] dp = new int[n][3];
            dp[0][0] = stickers[0][0]; dp[0][1] = stickers[1][0]; dp[0][2] = 0;
            for (int i = 1; i < n; i++) {
                dp[i][0] = stickers[0][i] + Math.max(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = stickers[1][i] + Math.max(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]);

                result = Math.max(dp[i][0], dp[i][1]);
                result = Math.max(result, dp[i][2]);
            }

            bw.write(result + "\n");
        }
        bw.flush();
    }
}

