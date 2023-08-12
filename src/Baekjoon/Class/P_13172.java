package Baekjoon.Class;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class P_13172 {

    static final int X = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());
        long ans = 0;
        while (m-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int n = line[0], s = line[1];
            int g = gcd(Math.max(n, s), Math.min(n, s));

            n /= g;
            s /= g;

            long b = power(n, X - 2);
            long q = (b * s) % X;

            ans += q;
            ans %= X;
        }
        bw.write(Long.toString(ans));
        bw.flush();
    }

    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    private static long power(int b, int exp) {
        if (exp == 1) return b;

        long sub = power(b, exp / 2);
        if (exp % 2 == 0) return (sub * sub) % X;
        else return (sub * sub) % X * b % X;
    }
}
