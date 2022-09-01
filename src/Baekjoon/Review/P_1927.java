package Baekjoon.Review;

import java.io.*;
import java.util.PriorityQueue;

public class P_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minPQ = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x != 0) minPQ.add(x);
            else {
                if (minPQ.isEmpty()) bw.write("0\n");
                else bw.write(minPQ.poll() + "\n");
            }
        }

        bw.flush();
    }
}
