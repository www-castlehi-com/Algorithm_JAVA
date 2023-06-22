package Baekjoon.Class;

import java.io.*;

public class P_27866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] split = br.readLine().split("");
        int i = Integer.parseInt(br.readLine());

        bw.write(split[i - 1]);
        bw.flush();
    }
}
