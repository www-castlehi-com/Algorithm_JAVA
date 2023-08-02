package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_11054 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[n];
        int[] rDp = new int[n];

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) cnt = Math.max(dp[j], cnt);
            }
            dp[i] = cnt + 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            int cnt = 0;
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j]) cnt = Math.max(rDp[j], cnt);
            }
            rDp[i] = cnt + 1;
        }

        int max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[i] + rDp[i] - 1);
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}
