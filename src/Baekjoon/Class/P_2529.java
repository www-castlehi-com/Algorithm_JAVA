package Baekjoon.Class;

import java.io.*;

public class P_2529 {
    static int k;
    static String[] inequality_sign;
    static String max = Long.toString(Long.MIN_VALUE);
    static String min = Long.toString(Long.MAX_VALUE);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = Integer.parseInt(br.readLine());
        inequality_sign = br.readLine().split(" ");

        find_integer("", 0, 0);
        bw.write(max);
        bw.newLine();
        bw.write(min);
        bw.flush();
    }

    public static void find_integer(String res, int idx, int digit) {
        if (idx == k) {
            if (max.compareTo(res) < 0) max = res;
            if (min.compareTo(res) > 0) min = res;
        }
        else {
            for (int i = 0; i <= 9; i++) {
                if (!overlap(res, i)) {
                    if (digit == 0) {
                        res = Integer.toString(i);
                        find_integer(res, idx, digit + 1);
                    } else {
                        if ((inequality_sign[idx].equals(">") && res.charAt(res.length() - 1) - '0' > i)
                                || (inequality_sign[idx].equals("<") && res.charAt(res.length() - 1) - '0' < i)) {
                            find_integer(res + Integer.toString(i), idx + 1, digit + 1);
                        }
                    }
                }
            }
        }
    }

    public static boolean overlap(String res, int num) {
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) - '0' == num)
                return true;
        }
        return false;
    }
}