package Baekjoon;

import java.io.*;
import java.util.Stack;

public class P_1918 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String exp = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            if (exp.charAt(i) >= 'A' && exp.charAt(i) <= 'Z') bw.write(exp.charAt(i));
            else if (exp.charAt(i) == '(') stack.push(exp.charAt(i));
            else if (exp.charAt(i) == ')') {
                while (true) {
                    if (stack.empty()) break;
                    Character pop = stack.pop();
                    if (pop == '(') break;
                    bw.write(pop);
                }
            }
            else {
                int targetPriority = getPriority(exp.charAt(i));
                while (true) {
                    if (!stack.empty() && (getPriority(stack.peek()) >= targetPriority)) bw.write(stack.pop());
                    else break;
                }
                stack.add(exp.charAt(i));
            }
        }

        while (!stack.empty()) {
            bw.write(stack.pop());
        }

        bw.flush();
    }

    private static int getPriority(char op) {
        if (op == '+' || op == '-') return 1;
        else if (op == '*' || op == '/') return 2;
        else return 0;
    }
}
