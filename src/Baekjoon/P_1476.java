package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P_1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int year = 1;
        int e = 1, s = 1, m = 1;
        while (year <= 7980) {
            if (e == arr[0] && s == arr[1] && m == arr[2]) break;
            year++;
            e++;
            s++;
            m++;
            if (e > 15) e = 1;
            if (s > 28) s = 1;
            if (m > 19) m = 1;
        }

        System.out.print(year);
    }
}
