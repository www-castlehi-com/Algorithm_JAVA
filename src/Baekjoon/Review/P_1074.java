package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;

public class P_1074 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], r = line[1], c = line[2];
        long size = (long) Math.pow(2, n);
        long cnt = 0;
        while (size > 0) {
            if (r < size && c >= size) {
                cnt += size * size;
                c -= size;
            }
            else if (r >= size && c < size) {
                cnt += (size * size) * 2;
                r -= size;
            }
            else if (r >= size && c >= size) {
                cnt += (size * size) * 3;
                r -= size;
                c -= size;
            }

            size /= 2;
        }

        bw.write(Long.toString(cnt));
        bw.flush();
    }
}
