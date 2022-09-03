package Baekjoon.Review;

import java.io.*;
import java.util.*;

public class P_18870 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] arr1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = arr1[i];
        }
        Arrays.sort(arr1);

        HashMap<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int i : arr1) {
            if (!map.containsKey(i)) map.put(i, idx++);
        }

        for (int i : arr2) {
            bw.write(map.get(i) + " ");
        }
        bw.flush();
    }
}
