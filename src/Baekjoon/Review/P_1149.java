package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] cost = new int[n][3];
        for (int i = 0; i < n; i++) cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[n + 1][3];
        for (int i = 1; i < n + 1; i++) {
            dp[i][0] = Math.min(dp[i - 1][1] + cost[i - 1][0], dp[i - 1][2] + cost[i - 1][0]);
            dp[i][1] = Math.min(dp[i - 1][0] + cost[i - 1][1], dp[i - 1][2] + cost[i - 1][1]);
            dp[i][2] = Math.min(dp[i - 1][0] + cost[i - 1][2], dp[i - 1][1] + cost[i - 1][2]);
        }

//        int min = Math.min(dp[n][0], dp[n][1]) < dp[n][2] ? Math.min(dp[n][0], dp[n][1]) : dp[n][2];
//        bw.write(Integer.toString(min));
        bw.write(Integer.toString(Arrays.stream(dp[n]).min().getAsInt()));
        bw.flush();
    }
}
