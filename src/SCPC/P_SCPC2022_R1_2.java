package SCPC;/*
You should use the standard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class P_SCPC2022_R1_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int curT = 1; curT <= t; curT++) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = line[0], p = line[1];

            int[][] dp = new int[n][2];

            int[] first = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] second = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            dp[0][0] = first[0];
            dp[0][1] = second[0];
            for (int i = 1; i < n; i++) {
                dp[i][0] = Math.max(dp[i - 1][0] + first[i] - p, dp[i - 1][1] + first[i]);
                dp[i][1] = Math.max(dp[i - 1][0] + second[i], dp[i - 1][1] + second[i] - p);
            }

            int res = Math.max(dp[n - 1][0], dp[n - 1][1]);
            bw.write("#" + curT + " " + res + "\n");
        }
        bw.flush();
    }
}