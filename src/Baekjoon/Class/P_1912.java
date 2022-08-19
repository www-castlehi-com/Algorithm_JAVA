package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] dp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int max = dp[0];

        for (int i = 1; i < n; i++) {
            if (dp[i - 1] > 0 && dp[i - 1] + dp[i] > 0) dp[i] = dp[i - 1] + dp[i];
            max = (max > dp[i]) ? max : dp[i];
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
