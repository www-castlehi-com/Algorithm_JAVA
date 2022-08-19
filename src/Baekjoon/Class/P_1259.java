package Baekjoon.Class;

import java.io.*;
import java.util.Deque;

public class P_1259 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {
            String n = br.readLine();
            if (n.equals("0")) break;

            StringBuffer sb = new StringBuffer();
            sb.append(n);
            StringBuffer sb_rev = sb.reverse();

            String n_rev = sb_rev.toString();

            if (n.equals(n_rev)) bw.write("yes\n");
            else bw.write("no\n");
        }
        bw.flush();
    }
}
