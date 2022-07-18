package Baekjoon;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class P_10816 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        HashMap<Integer, Integer> cards = new HashMap<>();
        for (int i : arr) {
            if (cards.containsKey(i)) cards.replace(i, cards.get(i) + 1);
            else cards.put(i, 1);
        }

        int m = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i : arr) {
            Integer count = cards.get(i);
            if (count == null) bw.write(0 + " ");
            else bw.write(cards.get(i) + " ");
        }
        bw.flush();
    }
}
