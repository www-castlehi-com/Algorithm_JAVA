package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class P_17219 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], m = line[1];
        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] words = br.readLine().split(" ");
            String domain = words[0], passwd = words[1];
            map.put(domain, passwd);
        }

        for (int i = 0; i < m; i++) {
            String domain = br.readLine();
            bw.write(map.get(domain) + "\n");
        }
        bw.flush();
    }
}
