package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int a = array[0];
        int b = array[1];

        int gcd = 1, lcm = 0;

        for (int i = 2; i <= (Math.min(a, b)); i++) {
            if (a % i == 0 && b % i == 0)
                gcd = i;
        }
        lcm = a * (b / gcd);
        bw.write(gcd + "\n");
        bw.write(Integer.toString(lcm));
        bw.flush();
    }
}
