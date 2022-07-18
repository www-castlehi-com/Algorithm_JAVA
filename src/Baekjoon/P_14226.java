package Baekjoon;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class P_14226 {

    static int size = 2000;
    static int s;
    static int[][] screen;
    static boolean[][] visited;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        s = Integer.parseInt(br.readLine());
        screen = new int[size][size];
        visited = new boolean[size][size];

        bfs();

        bw.write(Integer.toString(min));
        bw.flush();
    }

    public static void bfs() {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {1, 0});
        visited[1][0] = true;
        screen[1][0] = 0;

        while (!queue.isEmpty()) {
            int[] info = queue.remove();

            if (info[0] == s) {
                min = screen[info[0]][info[1]];
                return ;
            }

            if (!visited[info[0]][info[0]]) {
                queue.add(new int[] {info[0], info[0]});
                screen[info[0]][info[0]] = screen[info[0]][info[1]] + 1;
                visited[info[0]][info[0]] = true;
            }

            int copy = info[0] + info[1];
            if (copy < size && !visited[copy][info[1]] && info[1] != 0) {
                queue.add(new int[] {copy, info[1]});
                visited[copy][info[1]] = true;
                screen[copy][info[1]] = screen[info[0]][info[1]] + 1;
            }

            if (info[0] - 1 > 0 && !visited[info[0] - 1][info[1]]) {
                queue.add(new int[] {info[0] - 1 , info[1]});
                visited[info[0] - 1][info[1]] = true;
                screen[info[0] - 1][info[1]] = screen[info[0]][info[1]] + 1;
            }
        }
    }
}
