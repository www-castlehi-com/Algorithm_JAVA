package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_9466 {

    static int n;
    static int[] students;
    static int[] depth;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            n = Integer.parseInt(br.readLine());
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            students = new int[n + 1];
            depth = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                students[i] = line[i - 1];
                depth[students[i]]++;
            }

            topologicalSort();

            bw.write((n - Arrays.stream(depth).sum()) + "\n");
        }
        bw.flush();
    }

    private static void topologicalSort() {
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if (depth[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            Integer poll = queue.poll();

            int next = students[poll];

            depth[next]--;
            if(depth[next] == 0) queue.add(next);
        }
    }
}

