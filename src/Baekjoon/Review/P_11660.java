package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_11660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], m = line[1];
        int[][] graph = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 1; j <= n; j++) {
                graph[i][j] = line[j - 1];
                dp[i][j] = graph[i][j] + dp[i][j - 1];
            }
        }

        while (m-- > 0) {
            line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = line[0], y1 = line[1], x2 = line[2], y2 = line[3];

            int sum = 0;
            for (int i = x1; i <= x2; i++) sum += (dp[i][y2] - dp[i][y1 - 1]);

            bw.write(sum + "\n");
        }

        bw.flush();
    }
}
