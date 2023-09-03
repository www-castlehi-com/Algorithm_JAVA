package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_11049 {

    static Matrix[] matrices;

    static public class Matrix {
        int r, c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        matrices = new Matrix[n];
        for (int i = 0; i < n; i++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            matrices[i] = new Matrix(line[0], line[1]);
        }
        dp = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);

        bw.write(Integer.toString(solve(0, n - 1)));
        bw.flush();
    }

    private static int solve(int start, int end) {
        if (start == end) return dp[start][end] = 0;
        if (dp[start][end] != Integer.MAX_VALUE) return dp[start][end];

        for (int i = start; i < end; i++) {
            int ans = solve(start, i) + solve(i + 1, end) + (matrices[start].r * matrices[i].c * matrices[end].c);
            dp[start][end] = Math.min(dp[start][end], ans);
        }

        return dp[start][end];
    }
}

