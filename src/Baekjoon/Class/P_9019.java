package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class P_9019 {

    static boolean[] visited;

    public static class DSLR {
        int number;
        String command;

        public DSLR(int number, String command) {
            this.number = number;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] s = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int A = s[0], B = s[1];

            visited = new boolean[10001];
            bw.write(bfs(A, B) + "\n");
        }
        bw.flush();
    }

    private static String bfs(int a, int b) {
        LinkedList<DSLR> queue = new LinkedList<>();
        queue.add(new DSLR(a, ""));
        visited[a] = true;

        while (!queue.isEmpty()) {
            DSLR poll = queue.poll();

            if (poll.number == b) {
                return poll.command;
            }

            int pollInt = poll.number;

            int dInt = (pollInt * 2) % 10000;
            if (!visited[dInt]) {
                visited[dInt] = true;
                queue.add(new DSLR(dInt, poll.command + "D"));
            }

            int sInt = pollInt - 1;
            if (sInt == -1) sInt = 9999;
            if (!visited[sInt]) {
                visited[sInt] = true;
                queue.add(new DSLR(sInt, poll.command + "S"));
            }

            int lInt = (pollInt % 1000) * 10 + (pollInt / 1000);
            if (!visited[lInt]) {
                visited[lInt] = true;
                queue.add(new DSLR(lInt, poll.command + "L"));
            }

            int rInt = (pollInt % 10) * 1000 + (pollInt / 10);
            if (!visited[rInt]) {
                visited[rInt] = true;
                queue.add(new DSLR(rInt, poll.command + "R"));
            }
        }

        return "";
    }
}
