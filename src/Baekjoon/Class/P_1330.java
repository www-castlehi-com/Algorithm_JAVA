package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_1330 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (array[0] > array[1]) bw.write(">");
        else if (array[0] < array[1]) bw.write("<");
        else bw.write("==");

        bw.flush();
    }
}
