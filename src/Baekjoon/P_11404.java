package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        long[][] cities = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) Arrays.fill(cities[i], Integer.MAX_VALUE);
        for (int i = 0 ; i < m; i++) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (cities[s[0]][s[1]] > s[2]) cities[s[0]][s[1]] = s[2];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j<= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (j == k) cities[j][k] = 0;
                    else if (cities[j][k] > cities[j][i] + cities[i][k]) cities[j][k] = cities[j][i] + cities[i][k];
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j<= n; j++) {
                if (cities[i][j] == Integer.MAX_VALUE) cities[i][j] = 0;
                bw.write(cities[i][j] + " ");
            }
            bw.newLine();
        }
        bw.flush();
    }
}
