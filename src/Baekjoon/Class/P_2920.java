package Baekjoon.Class;

import java.io.*;

public class P_2920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String ascending = "1 2 3 4 5 6 7.md 8";
        String descending = "8 7.md 6 5 4 3 2 1";

        String numbers = br.readLine();

        if (numbers.equals(ascending)) bw.write("ascending");
        else if (numbers.equals(descending)) bw.write("descending");
        else bw.write("mixed");

        bw.flush();
    }
}
