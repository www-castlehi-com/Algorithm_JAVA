package Baekjoon;

import java.io.*;
import java.util.ArrayList;

public class P_6588 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Boolean> list = new ArrayList<>(1000001);
        list.add(false);
        list.add(false);
        for (int i = 2; i <= 1000000; i++) list.add(true);
        for (int i = 2; i * i <= 1000000; i++) {
            if (list.get(i))
                for (int j = i * i; j <= 1000000; j += i) list.set(j, false);
        }

        while (true) {
            int n = Integer.parseInt(br.readLine());
            boolean flag = false;
            if (n == 0) break;

            for (int i = 3; i <= n / 2; i++) {
                if (list.get(i) && list.get(n - i)) {
                    bw.write((Integer.toString(n) + " = " + Integer.toString(i) + " + " + Integer.toString(n - i)) + "\n");
                    flag = true;
                    break;
                }
            }
            if (!flag)
                bw.write("Goldbach's conjecture is wrong.\n");
        }
        bw.flush();
    }
}
