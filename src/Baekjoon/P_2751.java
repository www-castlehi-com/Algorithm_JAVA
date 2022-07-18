package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class P_2751 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < n; i++) integers.add(Integer.parseInt(br.readLine()));
        Collections.sort(integers);

        for (Integer integer : integers) {
            bw.write(integer + "\n");
        }
        bw.flush();
    }
}
