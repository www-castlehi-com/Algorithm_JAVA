package Baekjoon.Review;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_9019 {
    public static class Com {
        int num;
        String list;

        public Com(int num, String list) {
            this.num = num;
            this.list = list;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int a = line[0], b = line[1];

            bw.write(bfs(a, b) + "\n");
        }

        bw.flush();
    }

    private static String bfs(int a, int b) {
        boolean[] visited = new boolean[10000];
        LinkedList<Com> queue = new LinkedList<>();

        visited[a] = true;
        queue.add(new Com(a, ""));

        while (!queue.isEmpty()) {
            Com poll = queue.poll();

            if (poll.num == b) return poll.list;

            int next = (poll.num * 2) % 10000;
            if (!visited[next]) {
                queue.add(new Com(next, poll.list + "D"));
                visited[next] = true;
            }

            next = (poll.num - 1 > -1) ? poll.num - 1 : 9999;
            if (!visited[next]) {
                queue.add(new Com(next, poll.list + "S"));
                visited[next] = true;
            }

            next = (poll.num % 1000) * 10 + (poll.num / 1000);
            if (!visited[next]) {
                queue.add(new Com(next, poll.list + "L"));
                visited[next] = true;
            }

            next = (poll.num % 10) * 1000 + (poll.num / 10);
            if (!visited[next]) {
                queue.add(new Com(next, poll.list + "R"));
                visited[next] = true;
            }
        }

        return "";
    }
}
