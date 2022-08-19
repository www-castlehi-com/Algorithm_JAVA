package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_1248 {
    static int n;
    static String[] elem;
    static int[] result;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        elem = br.readLine().split("");
        result = new int[n];
        result_init();
        find_result(0);
    }

    public static void result_init() {
        int digit = 0;

        for (int i = 0; i < n; i++) {
            if (elem[digit].equals("-")) {
                result[i] = -1;
            }
            else if (elem[digit].equals("0")) {
                result[i] = 0;
            }
            else {
                result[i] = 1;
            }
            digit += (n - i);
        }
    }

    public static void find_result(int pos) throws IOException {
        if (pos == n) {
            for (int num : result) {
                bw.write(num + " ");
            }
            bw.flush();
            System.exit(0);
        }
        else {
            if (result[pos] > 0) {
                for (int i = 1; i <= 10; i++) {
                    result[pos] = i;
                    if (check(pos)) {
                        find_result(pos + 1);
                    }
                }
            }
            else if (result[pos] < 0) {
                for (int i = -1; i>= -10; i--) {
                    result[pos] = i;
                    if (check(pos)) {
                        find_result(pos + 1);
                    }
                }
            }
            else
                find_result(pos + 1);
        }
    }

    public static boolean check(int pos) {
        int digit = pos;
        int sum;

        for (int i = 0; i <= pos; i++) {
            sum = 0;

            for (int j = i; j <= pos; j++) {
                sum += result[j];
            }

            if (elem[digit].equals("+")) {
                if (sum <= 0) return false;
            }
            if (elem[digit].equals("-")) {
                if (sum >= 0) return false;
            }
            if (elem[digit].equals("0")) {
                if (sum != 0) return false;
            }

            digit += (n - 1 - i);
        }
        return true;
    }
}
