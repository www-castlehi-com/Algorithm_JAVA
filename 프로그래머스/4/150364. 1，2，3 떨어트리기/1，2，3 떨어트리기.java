import java.util.*;

class Solution {
    
    private List<Integer>[] tree;
    private int[] index;
    private int[] minimumCount;
    private int[] count;
    private List<Integer> order = new ArrayList<>();
    
    public int[] solution(int[][] edges, int[] target) {
        tree = new ArrayList[target.length + 1];
        index = new int[target.length + 1];
        minimumCount = new int[target.length + 1];
        count = new int[target.length + 1];
        
        initializeTree(edges);
        initializeMinimum(target);
        
        List<Integer> numbers = game(target);
        int[] answer = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            answer[i] = numbers.get(i);
        }
        return answer;
    }
    
    private void initializeTree(int[][] edges) {
        for (int i = 1; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            tree[edges[i][0]].add(edges[i][1]);
        }
        
        for (int i = 1; i < tree.length; i++) {
            Collections.sort(tree[i]);
        }
    }
    
    private void initializeMinimum(int[] target) {
        for (int i = 0; i < target.length; i++) {
            if (target[i] != 0) {
                int cur = target[i] % 3 == 0 ? target[i] / 3 : target[i] / 3 + 1;
                minimumCount[i + 1] = cur;
            }
        }
    }
    
    private List<Integer> game(int[] target) {
        while (isAllNodeYetCounting()) {
            int leaf = getNextLeaf();
            order.add(leaf);
            count[leaf]++;
        }
        
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < order.size(); i++) {
            int number = getNumber(target, count, order.get(i));
            if (number == -1) {
                numbers.clear();
                numbers.add(number);
                break;
            }
            numbers.add(number);
        }
        
        return numbers;
    }
    
    private boolean isAllNodeYetCounting() {
        for (int i = 1; i < count.length; i++) {
            if (minimumCount[i] > count[i]) {
                return true;
            }
        }
        
        return false;
    }
    
    private int getNextLeaf() {
        int cur = 1;
        while (!tree[cur].isEmpty()) {
            int leafSize = tree[cur].size();
            cur = tree[cur].get(index[cur]++ % leafSize);
        }
        return cur;
    }
    
    private int getNumber(int[] target, int[] count, int node) {
        for (int i = 1; i <= 3; i++) {
            if (target[node - 1] - i >= 0 && target[node - 1] - i <= (count[node] - 1) * 3) {
                target[node - 1] -= i;
                count[node]--;
                return i;
            }
        }
        return -1;
    }
}