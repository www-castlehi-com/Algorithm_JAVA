package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_2342 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] command = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int comLen = command.length;
        int[][][] dp = new int[comLen][5][5];
        for (int i = 0; i < comLen; i++) {
            for (int j = 0; j < 5; j++) Arrays.fill(dp[i][j], Integer.MAX_VALUE);
        }
        dp[0][0][0] = 0;

        for (int i = 1; i < comLen; i++) {
            int next = command[i - 1];
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (dp[i - 1][j][k] != Integer.MAX_VALUE) {
                        dp[i][next][k] = Math.min(dp[i][next][k], dp[i - 1][j][k] + getPower(j, next));
                        dp[i][j][next] = Math.min(dp[i][j][next], dp[i - 1][j][k] + getPower(k, next));
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j< 5; j++) {
                ans = Math.min(ans, dp[comLen - 1][i][j]);
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }

    private static int getPower(int cur, int next) {
        int dif = Math.abs(cur - next);

        if (cur == 0) return 2;
        else if (dif == 0) return 1;
        else if (dif == 1 || dif == 3) return 3;
        else return 4;
    }
}
