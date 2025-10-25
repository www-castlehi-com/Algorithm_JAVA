import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            int[] time = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            List<Integer>[] graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            int[] depth = new int[n + 1];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x].add(y);
                depth[y]++;
            }
            int w = Integer.parseInt(br.readLine());
            
            int result = topologySort(time, graph, depth, w);
            
            sb.append(result + "\n");
        }
        System.out.print(sb);
    }
    
    private static int topologySort(int[] time, List<Integer>[] graph, int[] depth, int w) {
        int[] dp = new int[graph.length + 1];
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> targets = getTargets(depth);
        for (int target : targets) {
            dp[target] = time[target - 1];
            queue.add(target);
        }
        
        while (!queue.isEmpty()) {
            int target = queue.poll();
            if (target == w) {
                break;
            }
            
            for (int nextTarget : graph[target]) {
                dp[nextTarget] = Math.max(dp[nextTarget], dp[target] + time[nextTarget - 1]);
                if (--depth[nextTarget] == 0) {
                    queue.add(nextTarget);
                }
            }
        }
        
        return dp[w];
    }
    
    private static List<Integer> getTargets(int[] depth) {
        List<Integer> targets = new ArrayList<>();
        for (int i = 1; i < depth.length; i++) {
            if (depth[i] == 0) {
                targets.add(i);
            }
        }
        return targets;
    }
}