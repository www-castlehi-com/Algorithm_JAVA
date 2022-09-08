package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = line[0], k = line[1];

        bw.write(Integer.toString(bfs(n, k)));
        bw.flush();
    }

    private static int bfs(int n, int k) {
        int[] loc = {-1, 1, 2};
        LinkedList<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[200001];
        visited[n] = true;
        queue.add(new int[]{n, 0});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();

            int curLoc = poll[0];
            if (curLoc == k) return poll[1];

            int nextLoc;
            for (int i = 0; i < 3; i++) {
                if (loc[i] != 2) nextLoc = curLoc - loc[i];
                else nextLoc = curLoc * loc[i];

                if (nextLoc >= 0 && nextLoc <= 200000 && !visited[nextLoc]) {
                    visited[nextLoc] = true;
                    queue.add(new int[]{nextLoc, poll[1] + 1});
                }
            }
        }
        return -1;
    }
}
