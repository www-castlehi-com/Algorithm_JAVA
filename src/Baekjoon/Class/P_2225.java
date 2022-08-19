package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[][] dp = new int[info[1] + 1][info[0] + 1];

        dp[0][0] = 1;
        for (int i = 1; i <= info[1]; i++) {
            for (int j = 0; j <= info[0]; j++) {
                for (int k = 0; k<= j; k++) {
                    dp[i][j] += dp[i - 1][j - k];
                    dp[i][j] %= 1000000000;
                }
            }
        }
        bw.write(Integer.toString(dp[info[1]][info[0]]));
        bw.flush();
    }
}
