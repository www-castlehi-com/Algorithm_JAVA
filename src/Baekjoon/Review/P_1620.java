package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class P_1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], m = line[1];

        HashMap<Integer, String> idxPocketmon = new HashMap<>();
        HashMap<String, Integer> namePocketmon = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String pocketmon = br.readLine();
            idxPocketmon.put(i + 1, pocketmon);
            namePocketmon.put(pocketmon, i + 1);
        }

        for (int i = 0; i < m; i++) {
            String input = br.readLine();
            if (input.charAt(0) >= '0' && input.charAt(0) <= '9') bw.write(idxPocketmon.get(Integer.parseInt(input)) + "\n");
            else bw.write(namePocketmon.get(input) + "\n");
        }

        bw.flush();
    }
}
