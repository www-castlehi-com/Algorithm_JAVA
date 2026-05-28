import java.util.*;

class Solution {
    
    private static int[] globalInfo;
    private static List<Integer>[] edgesPerNode;
    
    private static int maxSheep = 0;
    
    public int solution(int[] info, int[][] edges) {
        initGlobal(info, edges);
        
        calculate(0, 0, 0, new ArrayList<>());
        
        return maxSheep;
    }
    
    private void initGlobal(int[] info, int[][] edges) {
        globalInfo = info;
        edgesPerNode = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            edgesPerNode[i] = new ArrayList<>();
        }
        for (int[] edge: edges) {
            edgesPerNode[edge[0]].add(edge[1]);
        }
    }
    
    private void calculate(int curNode, int sheep, int wolf, List<Integer> availableNodes) {
        if (globalInfo[curNode] == 0) {
            sheep++;
            maxSheep = Math.max(maxSheep, sheep);
        } else {
            wolf++;
        }
        
        if (sheep <= wolf) {
            return ;
        }
        
        List<Integer> nextAvailable = new ArrayList<>(availableNodes);
        nextAvailable.remove(Integer.valueOf(curNode));
        for (Integer child: edgesPerNode[curNode]) {
            nextAvailable.add(child);
        }
        
        for (Integer nextNode: nextAvailable) {
            calculate(nextNode, sheep, wolf, nextAvailable);
        }
    }
}