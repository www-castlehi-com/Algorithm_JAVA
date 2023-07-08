package TossNEXT.Test;

import java.io.*;
import java.util.Arrays;

public class P_NEXT2023_7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] schedules = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int answer = 0;

        int n = schedules.length;

        int[] dp = new int[n + 1];
        dp[1] = schedules[0];
        dp[2] = Math.max(schedules[1], dp[1]);
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + schedules[i - 1]);
            dp[i] = Math.max(dp[i], dp[i - 3] + schedules[i - 1]);
        }

        answer = dp[n];
        System.out.println("answer = " + answer);
    }
}
