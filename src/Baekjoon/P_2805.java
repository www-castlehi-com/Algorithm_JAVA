package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class P_2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = arr[0], m = arr[1];
        int[] trees = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = 1, end = 2000000000;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;

            long treeSum = 0;
            for (int tree : trees) {
                if (tree - mid > 0) treeSum += (tree - mid);
            }

            if (treeSum >= m) {
                start = mid + 1;
            }
            else end = mid - 1;
        }

        bw.write(Integer.toString(end));
        bw.flush();
    }
}