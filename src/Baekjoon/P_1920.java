package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class P_1920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arrA = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arrA);
        int m = Integer.parseInt(br.readLine());
        int[] arrB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < m; i++) {
            int num = arrB[i];

            int start = 0, end = n - 1;
            boolean flag = false;
            while (start <= end) {
                int midIndex = (start + end) / 2;
                int midValue = arrA[midIndex];

                if (midValue == num) {
                    bw.write("1\n");
                    flag = true;
                    break;
                }
                else if (midValue < num) start = midIndex + 1;
                else end = midIndex - 1;
            }
            if (!flag) bw.write("0\n");
            bw.flush();
        }
    }
}
