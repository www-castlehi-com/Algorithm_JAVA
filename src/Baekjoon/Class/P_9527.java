package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_9527 {

    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        long[] line = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long a = line[0] , b = line[1];
        dp = new long[55];
        dp[0] = 1;
        for (int i = 1; i < 55; i++) dp[i] = dp[i - 1] * 2 + (1L << i);

        long res = solve(b) - solve(a - 1);
        bw.write(Long.toString(res));
        bw.flush();
    }

    private static long solve(long target) {
        int exp = (int) (Math.log(target) / Math.log(2));

        long ans = target & 1;
        for (int i = exp; i >= 1; i--) {
            if ((target & (1L << i)) != 0) {
                ans += dp[i - 1] + (target - (1L << i) + 1);
                target -= (1L << i);
            }
        }

        return ans;
    }
}
