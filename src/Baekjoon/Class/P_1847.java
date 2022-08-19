package Baekjoon.Class;

import java.io.*;
import java.util.Stack;

public class P_1847 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int idx = 1;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            while (idx <= num) {
                stack.push(idx);
                sb.append("+\n");
                idx++;
            }

            if (stack.peek() == num) {
                stack.pop();
                sb.append("-\n");
            }
            else {
                flag = false;
                break;
            }
        }

        if (flag) System.out.println(sb);
        else System.out.println("NO");
    }
}
