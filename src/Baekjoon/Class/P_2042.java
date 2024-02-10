package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2042 {

    static int n, m, k;
    static long[] arr;
    static long[] segmentTree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        int segmentTreeHeight = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        segmentTree = new long[1 << (segmentTreeHeight + 1) + 1];
        init(1, 1, n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            switch (a) {
                case 1:
                    update(b, 1, 1, n, b, b, c);
                    break;
                case 2:
                    sb.append(getCumulativeSum(1, 1, n, b, (int) c)).append("\n");
                    break;
            }
        }

        System.out.print(sb);
    }

    private static void init(int idx, int start, int end) {
        if (start == end) {
            segmentTree[idx] = arr[start];
        }
        else {
            init(idx * 2, start, (start + end) / 2);
            init(idx * 2 + 1, (start + end) / 2 + 1, end);
            segmentTree[idx] = segmentTree[idx * 2] + segmentTree[idx * 2 + 1];
        }
    }

    private static long getCumulativeSum(int idx, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return 0L;
        }
        else if (left <= start && right >= end) {
            return segmentTree[idx];
        }
        else {
            long leftSum = getCumulativeSum(idx * 2, start, (start + end) / 2, left, right);
            long rightSum = getCumulativeSum(idx * 2 + 1, (start + end) / 2 + 1, end, left, right);
            return leftSum + rightSum;
        }
    }

    private static void update(int arrIdx, int treeIdx, int start, int end, int left, int right, long value) {
        if (left > end || right < start) {
            return ;
        }

        if (start == end) {
            arr[arrIdx] = value;
            segmentTree[treeIdx] = value;
        }
        else {
            update(arrIdx, treeIdx * 2, start, (start + end) / 2, left, right, value);
            update(arrIdx, treeIdx * 2 + 1, (start + end) / 2 + 1, end, left, right, value);
            segmentTree[treeIdx] = segmentTree[treeIdx * 2] + segmentTree[treeIdx * 2 + 1];
        }
    }
}
