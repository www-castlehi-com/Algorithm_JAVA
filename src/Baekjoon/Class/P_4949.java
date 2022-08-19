package Baekjoon.Class;

import java.io.*;
import java.util.Stack;

public class P_4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        while (!(input = br.readLine()).equals(".")) {
            Stack<Character> stack = new Stack<>();
            Boolean flag = true;

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == '(' || input.charAt(i) == '[') stack.add(input.charAt(i));
                else if (input.charAt(i) == ')') {
                    if (stack.empty() || stack.peek() != '(') {
                        flag = false;
                        break;
                    }
                    else stack.pop();
                }
                else if (input.charAt(i) == ']') {
                    if (stack.empty() || stack.peek() != '[') {
                        flag = false;
                        break;
                    }
                    else stack.pop();
                }
            }
            if (!stack.empty()) flag = false;

            if (!flag) bw.write("no\n");
            else bw.write("yes\n");
        }
        bw.flush();
    }
}
