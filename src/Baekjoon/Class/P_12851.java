package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_12851 {

    static int n, k;
    static int[] time;
    static int minTime = Integer.MAX_VALUE;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0]; k = line[1];

        time = new int[200001];
        Arrays.fill(time, 100000);
        time[n] = 0;

        bfs();

        bw.write(time[k] + "\n" + cnt);
        bw.flush();
    }

    private static void bfs() {
        int[] dx = {1, -1, 2};
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(n);
        boolean[] visited = new boolean[200001];

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            if (poll == k && (minTime == Integer.MAX_VALUE || time[poll] == minTime)) cnt++;

            for (int i = 0; i < 3; i++) {
                int nextX = poll;
                if (dx[i] == 2) nextX *= 2;
                else nextX += dx[i];

                if (nextX >= 0 && nextX <= 200000 && time[nextX] > time[poll]) {
                    if (nextX == k) minTime = time[poll] + 1;
                    if (!visited[nextX]) {
                        time[nextX] = time[poll] + 1;
                        queue.add(nextX);
                    }
                }
            }
        }
    }
}
