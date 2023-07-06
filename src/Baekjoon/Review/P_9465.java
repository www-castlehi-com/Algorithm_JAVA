package Baekjoon.Review;

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

            int[][] dp = new int[n + 1][3];
            for (int i = 1; i <= n; i++) {
                dp[i][0] = stickers[0][i - 1] + Math.max(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = stickers[1][i - 1] + Math.max(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]);
            }

            int max = Arrays.stream(dp[n]).max().getAsInt();
            bw.write(max + "\n");
        }
        bw.flush();
    }
}
