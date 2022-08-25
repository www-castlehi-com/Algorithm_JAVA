package Baekjoon.Review;

import java.io.*;

public class P_1541 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] minus = br.readLine().split("-");

        int sum = Integer.MAX_VALUE;
        for (int i = 0; i < minus.length; i++) {
            String[] plus = minus[i].split("\\+");

            int subSum = 0;
            for (String s : plus) {
                subSum += Integer.parseInt(s);
            }

            if (sum == Integer.MAX_VALUE) sum = subSum;
            else sum -= subSum;
        }

        bw.write(Integer.toString(sum));
        bw.flush();
    }
}
