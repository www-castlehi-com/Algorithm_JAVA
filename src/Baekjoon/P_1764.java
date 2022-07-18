package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class P_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];
        HashSet<String> unknowns = new HashSet<>();
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            unknowns.add(br.readLine());
        }
        for (int i = 0; i < m; i++) {
            String s = br.readLine();
            if (unknowns.contains(s)) result.add(s);
        }

        bw.write(result.size() + "\n");
        Collections.sort(result);
        for (String s : result) {
            bw.write(s + "\n");
        }
        bw.flush();
    }
}
