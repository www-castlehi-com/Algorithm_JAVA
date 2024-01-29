package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class P_1289 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine());
            String memory = st.nextToken();
            int curMemory = 0;
            int cnt = 0;
            for (int j = 0 ; j < memory.length(); j++) {
                int targetMemory = memory.charAt(j) - '0';
                if (targetMemory != curMemory) {
                    cnt++;
                    curMemory = targetMemory;
                }
            }
             
            sb.append("#").append(test).append(" ").append(cnt).append("\n");
        }
        System.out.print(sb);
    }
}
