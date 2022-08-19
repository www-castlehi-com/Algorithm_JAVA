package Baekjoon.Class;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;

public class P_11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                Integer removeInt = heap.poll();
                if (removeInt == null) bw.write("0\n");
                else bw.write(removeInt + "\n");
            }
            else heap.add(num);
        }

        bw.flush();
    }
}
