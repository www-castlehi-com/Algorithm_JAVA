package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n];
        int[] r_dp = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(r_dp, 1);

        for(int i = 0; i < n; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) count = (count > dp[j]) ? count : dp[j];
            }
            dp[i] = count + 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            int count = 0;
            for (int j = n - 1; j > i; j--) {
                if (a[i] > a[j]) count = (count > r_dp[j]) ? count : r_dp[j];
            }
            r_dp[i] = count + 1;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = (max > dp[i] + r_dp[i] - 1) ? max : dp[i] + r_dp[i] - 1;
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
