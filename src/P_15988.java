import java.io.*;

public class P_15988 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[] n = new int[t + 1];
        for (int i = 1; i <= t; i++) n[i] = Integer.parseInt(br.readLine());
        long[][] dp = new long[1000001][4];

        dp[0][3] = 1;
        dp[1][1] = dp[2][1] = dp[2][2] = 1;
        for (int i = 3; i <= 1000000; i++) {
            dp[i][1] = (dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][3]) % 1000000009;
            dp[i][2] = (dp[i - 2][1] + dp[i - 2][2] + dp[i - 2][3]) % 1000000009;
            dp[i][3] = (dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3]) % 1000000009;
        }

        for (int i = 1; i<= t; i++) {
            bw.write(Long.toString((dp[n[i]][1] + dp[n[i]][2] + dp[n[i]][3]) % 1000000009));
            bw.newLine();
        }
        bw.flush();
    }
}
