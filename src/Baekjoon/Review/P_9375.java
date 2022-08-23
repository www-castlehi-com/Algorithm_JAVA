package Baekjoon.Review;

import java.io.*;
import java.util.HashMap;

public class P_9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            HashMap<String, Integer> clothes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                String[] line = br.readLine().split(" ");

                clothes.put(line[1], clothes.getOrDefault(line[1], 0) + 1);
            }

            int result = 1;
            for (Integer value : clothes.values()) {
                result *= (value + 1);
            }

            bw.write((result - 1) + "\n");
            bw.flush();
        }
    }
}
