package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class P_18110 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> comments = new ArrayList<>();
        for (int i = 0; i < n; i++) comments.add(Integer.parseInt(br.readLine()));
        Collections.sort(comments);

        int limit = (int) Math.round(n * 0.15);

        int sum = 0;
        for (int i = limit; i < n - limit; i++) sum += comments.get(i);

        int result = (int) Math.round((double) sum / (n - (limit * 2)));

        bw.write(Integer.toString(result));
        bw.flush();
    }
}
