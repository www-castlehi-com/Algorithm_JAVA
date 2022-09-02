package Baekjoon.Review;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P_11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (maxPQ.isEmpty()) bw.write("0\n");
                else bw.write(maxPQ.poll() + "\n");
            } else maxPQ.add(x);
        }
        bw.flush();
    }
}
