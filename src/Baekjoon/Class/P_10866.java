package Baekjoon.Class;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class P_10866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            Object[] command = Arrays.stream(br.readLine().split(" ")).toArray();

            if (command[0].equals("push_front")) deque.addFirst(Integer.parseInt((String) command[1]));
            else if (command[0].equals("push_back")) deque.add(Integer.parseInt((String) command[1]));
            else if (command[0].equals("pop_front")) {
                if (deque.isEmpty()) bw.write(-1 + "\n");
                else bw.write(deque.removeFirst() + "\n");
            }
            else if (command[0].equals("pop_back")) {
                if (deque.isEmpty()) bw.write(-1 + "\n");
                else bw.write(deque.removeLast() + "\n");
            }
            else if (command[0].equals("size")) bw.write(deque.size() + "\n");
            else if (command[0].equals("empty")) {
                if (deque.isEmpty()) bw.write(1 + "\n");
                else bw.write(0 + "\n");
            }
            else if (command[0].equals("front")) {
                if (deque.isEmpty()) bw.write(-1 + "\n");
                else bw.write(deque.getFirst() + "\n");
            }
            else {
                if (deque.isEmpty()) bw.write(-1 + "\n");
                else bw.write(deque.getLast() + "\n");
            }
        }

        bw.flush();
    }
}
