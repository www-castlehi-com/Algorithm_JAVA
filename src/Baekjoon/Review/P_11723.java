package Baekjoon.Review;

import java.io.*;
import java.util.HashSet;

public class P_11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");

            if (line[0].equals("add")) set.add(Integer.parseInt(line[1]));
            else if (line[0].equals("remove") && set.contains(Integer.parseInt(line[1])))
                set.remove(Integer.parseInt(line[1]));
            else if (line[0].equals("check")) {
                if (set.contains(Integer.parseInt(line[1]))) bw.write("1\n");
                else bw.write("0\n");
            }
            else if (line[0].equals("toggle")) {
                if (set.contains(Integer.parseInt(line[1]))) set.remove(Integer.parseInt(line[1]));
                else set.add(Integer.parseInt(line[1]));
            }
            else if (line[0].equals("all")) for (int j = 1; j <= 20; j++) set.add(j);
            else set = new HashSet<>();
        }

        bw.flush();
    }
}
