package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;

public class P_3052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int n = Integer.parseInt(br.readLine());
            if (!nums.contains(n % 42)) nums.add(n % 42);
        }

        bw.write(Integer.toString(nums.size()));
        bw.flush();
    }
}
