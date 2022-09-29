package Baekjoon.Review;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class P_11286 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) return o1[1] - o2[1];
                return o1[0] - o2[0];
            }
        });

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x != 0) {
                heap.add(new int[] {Math.abs(x), x});
            }
            else {
                if (!heap.isEmpty()) {
                    int[] poll = heap.poll();
                    bw.write(poll[1] + "\n");
                }
                else bw.write("0\n");
            }
        }

        bw.flush();
    }
}
