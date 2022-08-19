package Baekjoon.Class;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class P_10773 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < k; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num != 0) stack.push(num);
            else stack.pop();
        }

        int sum = 0;
        for (Integer integer : stack) {
            sum += integer;
        }

        bw.write(Integer.toString(sum));
        bw.flush();
    }
}
