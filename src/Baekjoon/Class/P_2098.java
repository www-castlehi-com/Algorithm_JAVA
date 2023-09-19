package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Stream;

public class P_2098 {

    static int n;
    static int[][] matrix;
    static int[][] dp;

    static int MAX;
    static final int INF = 16_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        matrix = new int[n][n];
        for (int i = 0; i < n; i++) matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        MAX = (1 << n) - 1;
        dp = new int[n][(int)Math.pow(2, n)];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);

        bw.write(Integer.toString(dfs(0, 1)));
        bw.flush();

        for(int i = 0; i < n; i++) {
            for (int j = 0; j <= MAX; j++) System.out.print(dp[i][j] + " ");
            System.out.println();
        }
    }

    private static int dfs(int curCity, int curBit) {
        if (curBit == MAX) {
            if (matrix[curCity][0] != 0) return matrix[curCity][0];
            else return INF;
        }

        if (dp[curCity][curBit] != -1) return dp[curCity][curBit];
        dp[curCity][curBit] = INF;

        for (int i = 0; i < n; i++) {
            if ((curBit & (1 << i)) == 0 && matrix[curCity][i] != 0) {
                dp[curCity][curBit] = Math.min(dp[curCity][curBit], matrix[curCity][i] + dfs(i, (curBit | 1 << i)));
            }
        }

        return dp[curCity][curBit];
    }
}
