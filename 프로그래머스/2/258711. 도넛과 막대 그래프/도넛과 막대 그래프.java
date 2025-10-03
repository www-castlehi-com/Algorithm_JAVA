import java.util.*;

class Solution {
    
    private static final Integer MAX_VERTEX = 1_000_000;
    
    private List<Integer>[] exitGraph = new ArrayList[MAX_VERTEX + 1];
    
    private int[] answer = new int[4];
    
    public int[] solution(int[][] edges) {
        answer[0] = getNewNode(edges);
        
        checkGraphType(answer[0]);
        
        return answer;
    }
    
    private int getNewNode(int[][] edges) {
        int[] entryCount = new int[MAX_VERTEX + 1];
        exitGraph = new ArrayList[MAX_VERTEX + 1];
        for (int i = 0; i <= MAX_VERTEX; i++) {
            exitGraph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            exitGraph[edges[i][0]].add(edges[i][1]);
            entryCount[edges[i][1]]++;
        }
        
        int maxDegree = 0;
        int newNode = 0;
        for (int i = 1; i <= MAX_VERTEX; i++) {
            if (entryCount[i] != 0 || !exitGraph[i].isEmpty()) {
                int curDegree = exitGraph[i].size() - entryCount[i];
                if (maxDegree < curDegree) {
                    maxDegree = curDegree;
                    newNode = i;
                }
            }
        }
        
        return newNode;
    }
    
    private void checkGraphType(int startNode) {
        for (int connectNode : exitGraph[startNode]) {
            Queue<Integer> queue = new LinkedList<>();
            queue.addAll(exitGraph[connectNode]);
            
            boolean isCirculated = false;
            while (!queue.isEmpty()) {
                int curNode = queue.poll();
                
                if (curNode == connectNode) {
                    isCirculated = true;
                    break;
                } else {
                    queue.addAll(exitGraph[curNode]);
                }
            }
            
            if (!isCirculated) {
                answer[2]++;
            } else {
                if (queue.isEmpty()) {
                    answer[1]++;
                } else {
                    answer[3]++;
                }
            }
        }
    }
}