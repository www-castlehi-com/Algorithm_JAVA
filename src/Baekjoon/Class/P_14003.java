package Baekjoon.Class;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class P_14003 {

    static int n;
    static int[] arr;
    static int[] idx;
    static ArrayList<Integer> seq = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        seq.add(arr[0]);
        idx = new int[n];
        idx[0] = 0;
        for (int i = 1; i < n; i++) {
            if (seq.get(seq.size() - 1) < arr[i]) {
                seq.add(arr[i]);
                idx[i] = seq.size() - 1;
            }
            else idx[i] = binarySearch(arr[i]);
        }

        int curIdx = seq.size() - 1;
        int[] ans = new int[seq.size()];
        for (int i = n - 1; i >= 0; i--) {
            if (idx[i] == curIdx) {
                ans[curIdx] = arr[i];
                curIdx--;
            }
        }

        bw.write(seq.size() + "\n");
        for (int i = 0; i < seq.size(); i++) bw.write(ans[i] + " ");
        bw.flush();
    }

    private static int binarySearch(int num) {
        int start = 0, end = seq.size() - 1;
        while (start < end) {
            int mid = (start + end) / 2;

            if (seq.get(mid) < num) start = mid + 1;
            else end = mid;
        }
        seq.set(end, num);

        return end;
    }
}
