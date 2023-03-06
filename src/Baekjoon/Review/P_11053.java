package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_11053 {

    static int n;
    static int[] line;
    static int res = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        solve();

        bw.write(Integer.toString(res));
        bw.flush();
    }

    private static void solve () {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (line[i] > line[j])
                    dp[i] = (dp[i] < dp[j] + 1) ? dp[j] + 1 : dp[i];
            }

            if (res < dp[i]) res = dp[i];
        }
    }
}
