package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class P_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] rgb = new int[n + 1][3];
        int[][] dp = new int[n + 1][3];

        for (int i = 1; i<= n; i++) rgb[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < 3; i++) dp[1][i] = rgb[1][i];
        for (int i = 2; i<= n; i++) {
            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + rgb[i][0];
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + rgb[i][1];
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + rgb[i][2];
        }

        bw.write(Integer.toString(Arrays.stream(dp[n]).min().getAsInt()));
        bw.flush();
    }
}
