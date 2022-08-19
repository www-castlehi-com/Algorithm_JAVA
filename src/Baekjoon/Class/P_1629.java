package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_1629 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long a = arr[0], b = arr[1], c = arr[2];

        long result = Power(a, b, c);

        bw.write(Long.toString(result));
        bw.flush();
    }

    private static long Power(long a, long b, long c) {
        if (a == 0) return 0;
        if (b == 0) return 1;
        if (b == 1) return a % c;

        long divide = Power(a, b / 2, c);

        if (b % 2 == 0) {
            return (divide * divide) % c;
        }
        else {
            return (divide * divide % c) * a % c;
        }
    }
}
