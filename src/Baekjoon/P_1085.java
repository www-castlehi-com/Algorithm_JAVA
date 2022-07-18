package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_1085 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = arr[0], y = arr[1], w = arr[2], h = arr[3];

        int min = Integer.MAX_VALUE;
        if (x < min) min = x;
        if (y < min) min = y;
        if (w - x < min) min = w - x;
        if (h - y < min) min = h - y;

        bw.write(Integer.toString(min));
        bw.flush();
    }
}
