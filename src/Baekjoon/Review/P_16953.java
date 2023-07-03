package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_16953 {

    public static class Exchange {
        long num;
        int cnt;

        public Exchange(long num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int a = line[0], b = line[1];

        bw.write(Integer.toString(bfs(a, b)));
        bw.flush();
    }

    private static int bfs(int a, int b) {
        LinkedList<Exchange> queue = new LinkedList<>();
        queue.add(new Exchange(a, 1));

        while (!queue.isEmpty()) {
            Exchange poll = queue.poll();

            long num = poll.num;
            int cnt = poll.cnt;

            if (num == b) return cnt;

            long mul2 = num * 2;
            long plusDigit = num * 10 + 1;

            if (mul2 <= b) {
                queue.add(new Exchange(mul2, cnt + 1));
            }
            if (plusDigit <= b) {
                queue.add(new Exchange(plusDigit, cnt + 1));
            }
        }

        return -1;
    }
}