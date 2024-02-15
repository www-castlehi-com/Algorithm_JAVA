package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_2357 {

    static final int MIN_VALUE = 1;
    static final int MAX_VALUE = 1_000_000_000;

    static int n, m;
    static int[] minTree;
    static int[] maxTree;
    static int[] arr;

    static StringBuilder sb = new StringBuilder();

    static int min;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); m = Integer.parseInt(st.nextToken());
        int treeHeight = (int) Math.ceil(Math.log(n) / Math.log(2));
        minTree = new int[1 << (treeHeight + 1) + 1];
        maxTree = new int[1 << (treeHeight + 1) + 1];
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
        initTree(1, 1, n);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            min = MAX_VALUE;
            max = MIN_VALUE;
            getMinAndMaxValue(1, 1, n, a, b);

            sb.append(min).append(" ").append(max).append("\n");
        }
        System.out.print(sb);
    }

    private static void getMinAndMaxValue(int idx, int start, int end, int left, int right) {
        if (left > end || right < start) {
            return ;
        }
        else if (left <= start && right >= end) {
            min = Math.min(min, minTree[idx]);
            max = Math.max(max, maxTree[idx]);
        }
        else {
            getMinAndMaxValue(idx * 2, start, (start + end) / 2, left, right);
            getMinAndMaxValue(idx * 2 + 1, (start + end) / 2 + 1, end, left, right);
        }
    }

    private static void initTree(int idx, int start, int end) {
        if (start == end) {
            minTree[idx] = arr[start];
            maxTree[idx] = arr[start];
        }
        else {
            initTree(idx * 2, start, (start + end) / 2);
            initTree(idx * 2 + 1, (start + end) / 2 + 1, end);
            minTree[idx] = Math.min(minTree[idx * 2], minTree[idx * 2 + 1]);
            maxTree[idx] = Math.max(maxTree[idx * 2], maxTree[idx * 2 + 1]);
        }
    }
}
