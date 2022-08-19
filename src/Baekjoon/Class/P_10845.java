package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P_10845 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new LinkedList<>();
        int back = 0;

        for (int i = 0; i < n; i++) {
            Object[] command = Arrays.stream(br.readLine().split(" ")).toArray();

            if (command[0].equals("push")) {
                back = Integer.parseInt((String) command[1]);
                queue.add(back);
            }
            else if (command[0].equals("pop")) {
                if (queue.isEmpty()) bw.write(-1 + "\n");
                else bw.write(queue.remove() + "\n");
            }
            else if (command[0].equals("size")) bw.write(queue.size() + "\n");
            else if (command[0].equals("empty")) {
                if (queue.isEmpty()) bw.write(1 + "\n");
                else bw.write(0 + "\n");
            }
            else if (command[0].equals("front")) {
                if (queue.isEmpty()) bw.write(-1 + "\n");
                else bw.write(queue.peek() + "\n");
            }
            else {
                if (queue.isEmpty()) bw.write(-1 + "\n");
                else bw.write(back + "\n");
            }
        }

        bw.flush();
    }
}
