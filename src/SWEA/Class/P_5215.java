package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_5215 {

    static int n, l;
    static int[][] dp = new int[21][10001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        for (int test = 1; test <= tc; test++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());

                int t = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());
                for (int j = 1; j <= l; j++) {
                    if (j - k >= 0) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - k] + t);
                    }
                    else {
                        dp[i][j] = dp[i - 1][j];
                    }
                }
            }

            sb.append("#").append(test).append(" ").append(dp[n][l]).append("\n");
        }
        System.out.print(sb);
    }
}
