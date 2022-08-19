package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class P_16928 {

    static boolean[] visited = new boolean[101];
    static HashMap<Integer, Integer> ladderOrSnake = new HashMap<>();

    public static class SnakesAndLadders {
        int loc;
        int cnt;

        public SnakesAndLadders(int loc, int cnt) {
            this.loc = loc;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];
        for (int i = 0; i < n + m; i++) {
            arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ladderOrSnake.put(arr[0], arr[1]);
        }

        bw.write(Integer.toString(bfs()));
        bw.flush();
    }

    private static int bfs() {
        LinkedList<SnakesAndLadders> queue = new LinkedList<>();
        queue.add(new SnakesAndLadders(1, 0));
        visited[1] = true;

        while (!queue.isEmpty()) {
            SnakesAndLadders poll = queue.poll();

            if (poll.loc == 100) return poll.cnt;

            for (int i = 1; i <= 6; i++) {
                int nextLoc = poll.loc + i;
                if (ladderOrSnake.containsKey(nextLoc)) nextLoc = ladderOrSnake.getOrDefault(nextLoc, 0);

                if (nextLoc <= 100 && !visited[nextLoc]) {
                    visited[nextLoc] = true;
                    queue.add(new SnakesAndLadders(nextLoc, poll.cnt + 1));
                }
            }
        }

        return -1;
    }
}
