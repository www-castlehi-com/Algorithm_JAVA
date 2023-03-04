package Baekjoon.Review;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class P_2407 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], m = line[1];

        BigInteger[] arr = new BigInteger[101];
        arr[0] = BigInteger.valueOf(1);
        for (int i = 1; i <= 100; i++)
            arr[i] = arr[i - 1].multiply(BigInteger.valueOf(i));

        BigInteger res = arr[n].divide(arr[n - m].multiply(arr[m]));

        bw.write(String.valueOf(res));
        bw.flush();
    }
}
