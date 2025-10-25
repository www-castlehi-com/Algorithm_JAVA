import java.io.*;
import java.util.*;

public class Main {
    
    private static long[] segmentTree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] arr = new long[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i + 1] = Long.parseLong(br.readLine());
        }
        int segmentTreeHeight = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        segmentTree = new long[1 << (segmentTreeHeight + 1) + 1];
        init(arr, 1, 1, n);
        
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            if (a == 1) {
                change(1, 1, n, b, c - arr[(int) b]);
                arr[(int) b] = c;
            } else {
                sb.append(sum(1, 1, n, b, c) + "\n");
            }
        }
        
        System.out.print(sb);
    }
    
    private static void init(long[] arr, int index, long start, long end) {
        if (start == end) {
            segmentTree[index] = arr[(int) start];
        } else {
            init(arr, index * 2, start, (start + end) / 2);
            init(arr, index * 2 + 1, (start + end) / 2 + 1, end);
            segmentTree[index] = segmentTree[index * 2] + segmentTree[index * 2 + 1];
        }
    }
    
    private static void change(int node, int start, int end, long index, long dif) {
        if (index < start || index > end) {
            return ;
        }
        
        segmentTree[node] += dif;
        if (start == end) return ;
        int mid = (start + end) / 2;
        change(node * 2, start, (start + end) / 2, index, dif);
        change(node * 2 + 1, (start + end) / 2 + 1, end, index, dif);
    }
    
    private static long sum(int node, int start, int end, long left, long right) {
        if (left > end || right < start) {
            return 0;
        }
        
        if (left <= start && right >= end) {
            return segmentTree[node];
        }
        int mid = (start + end) / 2;
        return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left, right);
    }
}