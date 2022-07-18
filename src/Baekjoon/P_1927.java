package Baekjoon;

import java.io.*;
import java.util.PriorityQueue;

public class P_1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                Integer removeNum = heap.poll();
                if (removeNum == null) bw.write("0\n");
                else bw.write(removeNum + "\n");
            }
            else heap.add(num);
        }
        bw.flush();
    }
}
