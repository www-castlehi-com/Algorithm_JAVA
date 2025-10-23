import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] memory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxCost = 0;
        for (int c : cost) {
            maxCost += c;
        }
        int[] dp = new int[maxCost + 1];
        for (int i = 0; i < n; i++) {
            for (int j = maxCost; j >= cost[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]);
            }
        }
        
        for (int i = 0; i <= maxCost; i++) {
            if (dp[i] >= m) {
                sb.append(Integer.toString(i));
                break;
            }
        }
        
        System.out.print(sb);
    }
}