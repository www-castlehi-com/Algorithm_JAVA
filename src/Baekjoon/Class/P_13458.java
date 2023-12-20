package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class P_13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int b = line[0], c = line[1];

        long res = 0L;
        for (int i = 0; i < n; i++) {
            a[i] -= b;
            res++;
            if (a[i] > 0) {
                res += a[i] / c;
                if (a[i] % c != 0) res++;
            }
        }

        bw.write(Long.toString(res));
        bw.flush();
    }
}
