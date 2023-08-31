package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_7579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], m = line[1];
        int[] activate = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] deactivate = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int maxFee = Arrays.stream(deactivate).sum();
        int[][] dp = new int[n + 1][maxFee + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= maxFee; j++) {
                if (j < deactivate[i - 1]) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = Math.max(dp[i - 1][j - deactivate[i - 1]] + activate[i - 1], dp[i - 1][j]);
            }
        }

        int i;
        for (i = 0; i <= maxFee; i++) {
            if (dp[n][i] >= m) break;
        }

        bw.write(Integer.toString(i));
        bw.flush();
    }
}
