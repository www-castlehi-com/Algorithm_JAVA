package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_2675 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String[] s = br.readLine().split(" ");

            int r = Integer.parseInt(s[0]);
            String repeat = "";
            for (int j = 0; j < s[1].length(); j++) {
                for (int k = 0; k < r; k++) repeat += s[1].charAt(j);
            }
            bw.write(repeat + "\n");
        }
        bw.flush();
    }
}
