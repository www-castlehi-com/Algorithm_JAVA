import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class P_2407 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];

        BigInteger[][] dp = new BigInteger[101][101];

        dp[1][1] = dp[2][2] = BigInteger.valueOf(1);
        dp[2][1] = BigInteger.valueOf(2);
        for (int i = 3; i <= 100; i++) {
            for (int j = 1; j <= i; j++) {
                if (j == 1) dp[i][j] = BigInteger.valueOf(i);
                else if (j == i) dp[i][j] = BigInteger.valueOf(1);
                else {
                    dp[i][j] = BigInteger.valueOf(0);
                    dp[i][j] = dp[i][j].add(dp[i - 1][j - 1]);
                    dp[i][j] = dp[i][j].add(dp[i - 1][j]);
                }
            }
        }

        bw.write(dp[n][m] + "\n");
        bw.flush();
    }
}
