package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class P_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = br.readLine();
        String boom = br.readLine();
        int boomLength = boom.length();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.add(str.charAt(i));
            if (str.charAt(i) == boom.charAt(boomLength - 1) && stack.size() >= boomLength) {
                Boom(stack, boom);
            }
        }

        if (!stack.isEmpty()) {
            for (Character character : stack) {
                bw.write(character + "");
            }
        }
        else bw.write("FRULA");
        bw.flush();
    }

    private static void Boom(Stack<Character> stack, String boom) {
        int length = 0;
        for (int i = stack.size() - boom.length(); i < stack.size(); i++) {
            if (boom.charAt(length) == stack.get(i)) length++;
        }

        if (length == boom.length()) {
            for (int i = 0; i < length; i++) stack.pop();
        }
    }
}
