package Baekjoon.Class;

import java.io.*;

public class P_11654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int ascii = br.readLine().charAt(0);
        bw.write(Integer.toString(ascii));
        bw.flush();
    }
}
