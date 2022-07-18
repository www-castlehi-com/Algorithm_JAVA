package Baekjoon;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_16953 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long a = arr[0], b = arr[1];

        bw.write(Long.toString(bfs(a, b)));
        bw.flush();
    }

    private static long bfs(long a, long b) {
        LinkedList<long[]> queue = new LinkedList<>();
        queue.add(new long[]{a, 0});

        while (!queue.isEmpty()) {
            long[] poll = queue.poll();

            if (poll[0] == b) return poll[1] + 1;

            if (poll[0] * 2 <= b) queue.add(new long[]{poll[0] * 2, poll[1] + 1});
            if (poll[0] * 10 + 1 <= b) queue.add(new long[]{poll[0] * 10 + 1, poll[1] + 1});
        }

        return -1;
    }
}
