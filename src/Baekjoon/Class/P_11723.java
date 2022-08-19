package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;

public class P_11723 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int bit = 0;

        for (int i = 0; i < n; i++) {
            String[] calc = br.readLine().split(" ");

            switch (calc[0]) {
                case "add" :
                    bit |= (1 << Integer.parseInt(calc[1]));
                    break;
                case "remove" :
                    bit &= ~(1 << Integer.parseInt(calc[1]));
                    break;
                case "check" :
                    bw.write((bit & (1 << Integer.parseInt(calc[1]))) != 0 ? "1\n" : "0\n");
                    break;
                case "toggle" :
                    bit ^= (1 << Integer.parseInt(calc[1]));
                    break;
                case "all" :
                    bit |= ~(0);
                    break;
                case "empty" :
                    bit &= 0;
                    break;
            }
        }
        bw.flush();
    }
}
