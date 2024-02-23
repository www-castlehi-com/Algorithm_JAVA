package Baekjoon.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P_11505 {

    static final int MOD = 1_000_000_007;

    static int[] arr;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int treeHeight = (int) Math.ceil(Math.log(n) / Math.log(2));
        tree = new long[(1 << (treeHeight + 1)) + 1];
        Arrays.fill(tree, 1);

        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        initTree(1, 1, n);

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(1, 1, n, b, b, c);
            }
            else {
                sb.append(cumulativeMultiple(1, 1, n, b, c)).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void initTree(int idx, int start, int end) {
        if (start == end) {
            tree[idx] = arr[start];
        }
        else {
            initTree(idx * 2, start, (start + end) / 2);
            initTree(idx * 2 + 1, (start + end) / 2 + 1, end);
            tree[idx] = (tree[idx * 2] * tree[idx * 2 + 1]) % MOD;
        }
    }

    public static void update(int idx, int start, int end, int left, int right, int value) {
        if (right < start || left > end) {
            return ;
        }

        if (start == end) {
            tree[idx] = value;
        }
        else {
            update(idx * 2, start, (start + end) / 2, left, right, value);
            update(idx * 2 + 1, (start + end) / 2 + 1, end, left, right, value);
            tree[idx] = (tree[idx * 2] * tree[idx * 2 + 1]) % MOD;
        }
    }

    public static int cumulativeMultiple(int idx, int start, int end, int left, int right) {
        if (right < start || left > end) {
            return 1;
        }
        else if (left <= start && right >= end) {
            return (int) tree[idx];
        }
        else {
            long leftMultiple = cumulativeMultiple(idx * 2, start, (start + end) / 2, left, right);
            long rightMultiple = cumulativeMultiple(idx * 2 + 1, (start + end) / 2 + 1, end, left, right);
            return (int) ((leftMultiple * rightMultiple) % MOD);
        }
    }
}
