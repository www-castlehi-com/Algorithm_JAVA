import java.io.*;

public class P_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        long[] dp = new long[101];
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for (int i = 6; i <= 100; i++) dp[i] = dp[i - 1] + dp[i - 5];

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            bw.write(dp[n] + "\n");
        }

        bw.flush();
    }
}
