package Codeforce;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Div3_883_C {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int n = line[0], m = line[1], h = line[2];
            int[][] arr = new int[n][m];
            for (int i = 0; i < n; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Arrays.sort(arr[i]);
            }

            BigInteger[][] dp = new BigInteger[n][m + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= m; j++) dp[i][j] = BigInteger.valueOf(0);
            }

            BigInteger rudolfPoint = BigInteger.valueOf(0), rudolfPenalty = BigInteger.valueOf(0);
            for (int i = 1; i <= m; i++) {
                dp[0][i] = dp[0][i - 1].add(BigInteger.valueOf(arr[0][i - 1]));
                if (dp[0][i].compareTo(BigInteger.valueOf(h)) <= 0) {
                    rudolfPoint = rudolfPenalty.add(BigInteger.valueOf(1));
                    rudolfPenalty = rudolfPenalty.add(dp[0][i]);
                }
                else break;
            }

            int rudolfWin = 0;
            for (int i = 1; i < n; i++) {
                BigInteger partPoint = BigInteger.valueOf(0), partPenalty = BigInteger.valueOf(0);
                for (int j = 1; j <= m; j++) {
                    dp[i][j] = dp[i][j - 1].add(BigInteger.valueOf(arr[i][j - 1]));
                    if (dp[i][j].compareTo(BigInteger.valueOf(h)) <= 0) {
                        partPoint = partPenalty.add(BigInteger.valueOf(1));
                        partPenalty = partPenalty.add(dp[i][j]);
                    } else break;
                }

                if (partPoint.compareTo(rudolfPoint) < 0) rudolfWin++;
                else if (partPoint == rudolfPoint && partPenalty.compareTo(rudolfPenalty) <= 0) rudolfWin++;
            }

            bw.write((n - rudolfWin) + "\n");
        }
        bw.flush();
    }
}
