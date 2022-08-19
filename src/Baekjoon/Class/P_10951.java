package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class P_10951 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

       while(sc.hasNextInt()) {
           int n1 = sc.nextInt();
           int n2 = sc.nextInt();
           System.out.println(n1 + n2);
        }
        sc.close();
    }
}
