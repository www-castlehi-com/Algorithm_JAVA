package Baekjoon.Class;

import java.util.Scanner;

public class P_17427 {
    public static void main(String[] args) {
        long sum = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            sum += (long) (n / i) * i;
        }
        System.out.println(sum);
    }
}
