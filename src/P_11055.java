import java.io.*;
import java.util.Arrays;

public class P_11055 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n];
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (a[i] > a[j]) {
                    dp[i] = (dp[i] < dp[j]) ? dp[j] : dp[i];
                }
            }
            dp[i] += a[i];
            max = (max > dp[i]) ? max : dp[i];
        }

        bw.write(Integer.toString(max));
        bw.flush();
    }
}