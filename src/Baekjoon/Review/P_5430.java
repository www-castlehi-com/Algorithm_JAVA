package Baekjoon.Review;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class P_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());

            ArrayDeque<Integer> deque = new ArrayDeque<>();
            String matrix = br.readLine();

            if (n != 0) {
                StringTokenizer st = new StringTokenizer(matrix, "[,]");
                while (st.hasMoreTokens()) deque.add(Integer.parseInt(st.nextToken()));
            }

            int reverse = 1;
            boolean error = false;
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == 'R') reverse *= -1;
                else {
                    if (deque.isEmpty()) {
                        bw.write("error\n");
                        error = true;
                        break;
                    }
                    else {
                        if (reverse == 1) deque.pollFirst();
                        else deque.pollLast();
                    }
                }
            }

            if (!error) {
                bw.write("[");
                if (!deque.isEmpty()) {
                    if (reverse == 1) {
                        bw.write(Integer.toString(deque.pollFirst()));
                        while (!deque.isEmpty()) bw.write("," + deque.pollFirst());
                    } else {
                        bw.write(Integer.toString(deque.pollLast()));
                        while (!deque.isEmpty()) bw.write("," + deque.pollLast());
                    }
                }
                bw.write("]\n");
            }
        }

        bw.flush();
    }
}
