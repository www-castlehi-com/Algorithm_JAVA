package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_2098_2 {

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
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], INF);
        dp[0][1] = 0;

        bw.write(Integer.toString(solve()));
        bw.flush();

        for(int i = 0; i < n; i++) {
            for (int j = 0; j <= MAX; j++) System.out.print(dp[i][j] + " ");
            System.out.println();
        }
    }

    private static int solve() {
        for (int bit = 0; bit < (1 << n); bit++) {
            for (int cur = 0; cur < n; cur++) {
                if ((bit & (1 << cur)) == 0) continue;
                for (int prev = 0; prev < n; prev++) {
                    if ((bit & (1 << prev)) != 0 && cur != prev && matrix[prev][cur] != 0) {
                        dp[cur][bit] = Math.min(dp[cur][bit], dp[prev][bit ^ (1 << cur)] + matrix[prev][cur]);
                    }
                }
            }
        }

        int result = INF;
        for (int i = 1; i <n; i++) {
            if (matrix[i][0] != 0)
                result = Math.min(result, dp[i][MAX] + matrix[i][0]);
        }

        return result;
    }
}
