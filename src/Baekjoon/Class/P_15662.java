package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_15662 {

    static List<LinkedList<Integer>> gears;
    static int[][] rotation;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        gears = new ArrayList<>();
        for (int i = 0; i < t; i++) {
            int[] array = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            LinkedList<Integer> integers = new LinkedList<>();
            for (int i1 : array) {
                integers.add(i1);
            }
            gears.add(integers);
        }
        int k = Integer.parseInt(br.readLine());
        rotation = new int[k][2];
        for (int i = 0 ; i < k; i++) rotation[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        operational();

        bw.write(Integer.toString(check()));
        bw.flush();
    }

    public static void operational() {
        for (int i = 0 ; i < rotation.length; i++) {
            int num = rotation[i][0] - 1;
            int dir = rotation[i][1];

            precision_machining(gears.get(num).get(6), gears.get(num).get(2), num, num + 1, dir * -1);
            precision_machining(gears.get(num).get(6), gears.get(num).get(2), num, num - 1, dir * -1);
            adjustment(num, dir);
        }
    }

    public static void precision_machining(int p_left, int p_right, int p_num, int c_num, int dir) {
        if (c_num < 0 || c_num >= gears.size()) return;

        int c_left = gears.get(c_num).get(6);
        int c_right = gears.get(c_num).get(2);

        if (c_num < p_num) {
            if (p_left != c_right) {
                precision_machining(c_left, c_right, c_num, c_num - 1, dir * -1);
                adjustment(c_num, dir);
            }
        }
        else {
            if (p_right != c_left) {
                precision_machining(c_left, c_right, c_num, c_num + 1, dir * -1);
                adjustment(c_num, dir);
            }
        }
    }

    public static void adjustment(int num, int dir) {
        if (dir == 1) {
            gears.get(num).addFirst(gears.get(num).removeLast());
        }
        else {
            gears.get(num).addLast(gears.get(num).removeFirst());
        }
    }

    public static int check() {
        int cnt = 0;

        for (LinkedList<Integer> gear : gears) {
            if (gear.get(0) == 1) cnt++;
        }

        return cnt;
    }
}
