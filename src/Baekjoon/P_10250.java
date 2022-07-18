package Baekjoon;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

public class P_10250 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int h = arr[0], w = arr[1], n = arr[2];

            int floor = n % h;
            int address = n / h + 1;

            if (floor == 0) {
                floor = h;
                address = n / h;
            }

            System.out.printf("%d%02d\n", floor, address);
        }
    }
}
