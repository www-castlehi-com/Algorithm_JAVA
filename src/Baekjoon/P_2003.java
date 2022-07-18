package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_2003 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int ptr1, ptr2;
        ptr1 = ptr2 = 0;
        int sum = 0, cnt = 0;
        while (true) {
            if (sum >= m) sum -= numbers[ptr1++];
            else {
                if (ptr2 == n) break;
                sum += numbers[ptr2++];
            }

            if (sum == m) cnt++;
        }

        bw.write(Integer.toString(cnt));
        bw.flush();
    }
}
