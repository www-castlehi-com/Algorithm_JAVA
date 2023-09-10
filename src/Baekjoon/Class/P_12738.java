package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_12738 {

    static int n;
    static long[] arr;
    static ArrayList<Long> seq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        seq = new ArrayList<>();

        seq.add(arr[0]);
        for (int i = 1; i < n; i++) {
            if (arr[i] > seq.get(seq.size() - 1)) seq.add(arr[i]);
            else binarySearch(arr[i]);
        }

        bw.write(Integer.toString(seq.size()));
        bw.flush();
    }

    private static void binarySearch(long target) {
        int start = 0, end = seq.size() - 1;
        while (start < end) {
            int mid = (start + end) / 2;

            if (seq.get(mid) < target) start = mid + 1;
            else end = mid;
        }

        seq.set(end, target);
    }
}