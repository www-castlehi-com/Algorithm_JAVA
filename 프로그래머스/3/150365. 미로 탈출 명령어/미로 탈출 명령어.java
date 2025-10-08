import java.util.*;

class Solution {
    
    private class Move {
        int x;
        int y;
        String direction;
        
        Move(int x, int y, String direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
    
    private String[] direction = {"d", "l", "r", "u"};
    private int[] dx = {0, -1, 1, 0};
    private int[] dy = {1, 0, 0, -1};
    private boolean isSuccess = false;
    private String answer = "";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        dfs(n, m, r, c, y, x, k, 0, new StringBuilder());
        
        return isSuccess ? answer : "impossible";
    }
    
    private void dfs(int n, int m, int r, int c, int x, int y, int k, int depth, StringBuilder path) {
        if (isSuccess) {
            return ;
        }
        
        int dist = Math.abs(c - x) + Math.abs(r - y);
        int remain = k - depth;
        
        if (dist > remain || (remain - dist) % 2 != 0) {
            return ;
        }
        
        if (depth == k && x == c && r == y) {
            answer = path.toString();
            isSuccess = true;
            return ;
        }
        
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            
            if (nextX >= 1 && nextX <= m && nextY >= 1 && nextY <= n) {
                path.append(direction[i]);
                dfs(n, m, r, c, nextX, nextY, k, depth + 1, path);
                path.deleteCharAt(path.length() - 1);
                if (isSuccess) {
                    return ;
                }
            }
        }
    }
}