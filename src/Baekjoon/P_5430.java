package Baekjoon;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;

public class P_5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            String command = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String xs = br.readLine();

            ArrayDeque<Integer> numbers = new ArrayDeque<>();
            int num = 0;
            for (int i = 0; i < xs.length(); i++) {
                if (xs.charAt(i) >= '0' && xs.charAt(i) <= '9') num = num * 10 + xs.charAt(i) - '0';
                else if (xs.charAt(i) == ',' || xs.charAt(i) == ']') {
                    if (num != 0) {
                        numbers.add(num);
                        num = 0;
                    }
                }
            }

            boolean error = false;
            boolean reverse = false;
            for (int i = 0; i < command.length(); i++) {
                if (command.charAt(i) == 'R') reverse = !reverse;
                else {
                    if (numbers.isEmpty()) {
                        error = true;
                        bw.write("error\n");
                        break;
                    }
                    else {
                        if (reverse) numbers.pollLast();
                        else numbers.pollFirst();
                    }
                }
            }

            if (!error) {
                bw.write("[");
                if (!numbers.isEmpty()) {
                    if (reverse) {
                        bw.write(Integer.toString(numbers.pollLast()));
                        while (!numbers.isEmpty()) {
                            bw.write("," + numbers.pollLast());
                        }
                    } else {
                        bw.write(Integer.toString(numbers.pollFirst()));
                        while (!numbers.isEmpty()) {
                            bw.write("," + numbers.pollFirst());
                        }
                    }
                }
                bw.write("]\n");
            }
        }

        bw.flush();
    }
}
