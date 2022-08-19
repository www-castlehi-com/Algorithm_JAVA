package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class P_10952 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

       while (true) {
           int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

           if (array[0] == 0 && array[1] == 0) break;
           bw.write((array[0] + array[1]) + "\n");
       }
       bw.flush();
    }
}
