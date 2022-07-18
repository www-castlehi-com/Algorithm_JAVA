package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class P_9012 {

    static String[] brackets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            brackets = br.readLine().split("");
            bw.write(FindVPS());
            bw.newLine();
        }
        bw.flush();
    }

    public static String FindVPS() {
        Stack<String> stack = new Stack<>();
        for (String bracket : brackets) {
            if (stack.empty()) {
                if (bracket.equals("(")) stack.add(bracket);
                else return "NO";
            }
            else {
                if (bracket.equals("(")) stack.add(bracket);
                else {
                    if (!stack.peek().equals("(")) return "NO";
                    else stack.pop();
                }
            }
        }

        if (stack.empty()) return "YES";
        else return "NO";
    }
}
