package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        ArrayList<Boolean> list = new ArrayList<>(1000001);
        list.add(false);
        list.add(false);
        for (int i = 2; i <= 1000000; i++)
            list.add(true);

        for (int i = 2; i * i <= 1000000; i++) {
            if (list.get(i))
                for (int j = i * i; j <= 1000000; j += i) list.set(j, false);
        }

        for (int i = array[0]; i <= array[1]; i++) {
            if (list.get(i))
                bw.write(i + "\n");
        }
        bw.flush();
    }
}
