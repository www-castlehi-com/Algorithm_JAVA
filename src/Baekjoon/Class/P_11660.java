package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];

        int[][] matrix = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            int[] board = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = board[j - 1];
                if (i > 0) dp[i][j] = dp[i - 1][j];
                for (int k = 0; k <= j; k++) dp[i][j] += matrix[i][k];
            }
        }

        for (int i = 0; i < m; i++) {
            int[] xy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int y1 = xy[0], x1 = xy[1], y2 = xy[2], x2 = xy[3];

            int result = dp[y2][x2] - dp[y2][x1 - 1] - dp[y1 - 1][x2] + dp[y1 -1][x1 - 1];
            bw.write(result + "\n");
        }
        bw.flush();
    }
}
