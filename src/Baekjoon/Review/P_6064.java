package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int m = line[0], n = line[1], x = line[2], y = line[3];

            int lcm = getLCM(m, n, Math.min(m, n));

            boolean flag = false;
            for (int i = x; i <= lcm; i += m) {
                if (y == n && i % n == 0 || i % n == y) {
                    flag = true;
                    bw.write(i + "\n");
                    break;
                }
            }
            if (!flag) bw.write("-1\n");
        }
        bw.flush();
    }

    private static int getLCM(int m, int n, int min) {
        int lcm = 1;

        for (int i = 2; i <= min; i++) {
            if (m % i == 0 && n % i == 0) {
                lcm = i;
            }
        }

        return lcm * (m / lcm) * (n / lcm);
    }
}
