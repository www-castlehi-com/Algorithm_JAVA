package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_6064 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        int[][] calc = new int[t][4];
        for (int i = 0; i < t; i++) {
            calc[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            bw.write(find_year(calc[i]) + "\n");
        }
        bw.flush();
    }

    public static int find_lcm(int m, int n) {
        int lcm = 1;

        if (m > n) {
            for (int i = 2; i <= n; i++) {
                if (m % i == 0 && n % i == 0) {
                    lcm = i;
                }
            }
        }
        else {
            for (int i = 2; i <= m; i++) {
                if (m % i == 0 && n % i == 0) lcm = i;
            }
        }

        return lcm * (m / lcm) * (n / lcm);
    }

    public static int find_year(int[] calc) {
        int lcm = find_lcm(calc[0], calc[1]);

        int year = calc[2];
        boolean flag = false;
        while (year <= lcm) {
            if (calc[3] == calc[1] && year % calc[1] == 0 || calc[3] == year % calc[1]) {
                flag = true;
                break;
            }
            year += calc[0];
        }

        if (flag) return year;
        else return -1;
    }
}
