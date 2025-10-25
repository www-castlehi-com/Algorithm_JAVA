import java.io.*;
import java.util.*;

public class Main {
    
    private static int[] set;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        set = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            set[i] = i;
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int com = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if (com == 0) {
                union(a, b);
            } else {
                int aParent = find(a);
                int bParent = find(b);
                sb.append(aParent == bParent ? "YES\n" : "NO\n");
            }
        }
        
        System.out.print(sb);
    }
    
    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        
        if (a != b) {
            if (a < b) {
                set[b] = a;
            } else {
                set[a] = b;
            }
        }
    }
    
    private static int find(int a) {
        if (set[a] == a) {
            return a;
        }
        return set[a] = find(set[a]);
    }
}