package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

public class P_1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], m = line[1];
        HashSet<String> hashSet = new HashSet<>();
        TreeSet<String> treeSet = new TreeSet<>();
        for (int i = 0; i < n; i++) hashSet.add(br.readLine());
        for (int i = 0; i < m; i++) {
            String name = br.readLine();

            if (hashSet.contains(name)) treeSet.add(name);
        }

        int size = treeSet.size();
        bw.write(size + "\n");
        for (String s : treeSet) {
            bw.write(s + "\n");
        }
        bw.flush();
    }
}
