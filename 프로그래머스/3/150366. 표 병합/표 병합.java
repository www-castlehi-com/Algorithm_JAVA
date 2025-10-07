import java.util.*;

class Solution {
    
    private static final String EMPTY = "EMPTY";
    
    private class Cell {
        int r;
        int c;
        String value;
        List<Cell> merges = new ArrayList<>();
        
        Cell(int r, int c, String value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }
    
    private Cell[][] cells = new Cell[51][51];
    
    public String[] solution(String[] commands) {
        List<String> prints = new ArrayList<>();
        
        for (int i = 1; i <= 50; i++) {
            for (int j = 1; j <= 50; j++) {
                cells[i][j] = new Cell(i, j, null);
            }
        }
        
        for (String command : commands) {
            String[] s = command.split(" ");
            
            switch (s[0]) {
                case "UPDATE":
                    update(s);
                    break;
                case "MERGE" :
                    merge(s);
                    break;
                case "UNMERGE" :
                    unmerge(s);
                    break;
                case "PRINT" :
                    prints.add(print(s));
                    break;
            }
        }
        
        String[] answer = new String[prints.size()];
        for (int i = 0 ; i < prints.size(); i++) {
            answer[i] = prints.get(i);
        }
        return answer;
    }
    
    private void update(String[] s) {
        boolean[][] visited = new boolean[51][51];
        try {
            int r = Integer.valueOf(s[1]);
            int c = Integer.valueOf(s[2]);
            String value = s[3];
            
            updateMerge(cells[r][c], value);
        } catch (Exception e) {
            String value1 = s[1];
            String value2 = s[2];
            
            for (int i = 1; i <= 50; i++) {
                for (int j = 1; j <= 50; j++) {
                    if (cells[i][j].value != null && cells[i][j].value.equals(value1)) {
                        updateMerge(cells[i][j], value2);
                    }
                }
            }
        }
    }
    
    private void merge(String[] s) {
        int r1 = Integer.valueOf(s[1]);
        int c1 = Integer.valueOf(s[2]);
        int r2 = Integer.valueOf(s[3]);
        int c2 = Integer.valueOf(s[4]);
        
        if (r1 == r2 && c1 == c2) {
            return;
        }
        
        if (cells[r1][c1].value != null) {
            updateMerge(cells[r2][c2], cells[r1][c1].value);
        } else {
            updateMerge(cells[r1][c1], cells[r2][c2].value);
        }
        
        cells[r1][c1].merges.add(cells[r2][c2]);
        cells[r2][c2].merges.add(cells[r1][c1]);
    }
    
    private void unmerge(String[] s) {
        boolean[][] visited = new boolean[51][51];
        int r = Integer.valueOf(s[1]);
        int c = Integer.valueOf(s[2]);
        
        Queue<Cell> queue = new LinkedList<>();
        queue.add(cells[r][c]);
        String curValue = cells[r][c].value;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();   
            visited[cell.r][cell.c] = true;
            
            for (Cell m : cell.merges) {
                if (!visited[m.r][m.c]) {
                    queue.add(m);
                }
            }
            
            cell.merges.clear();
            cell.value = null;
        }
        
        cells[r][c].value = curValue;
    }
    
    private String print(String[] s) {
        int r = Integer.valueOf(s[1]);
        int c = Integer.valueOf(s[2]);
        
        return cells[r][c].value == null ? EMPTY : cells[r][c].value;
    }
    
    private void updateMerge(Cell target, String value) {
        boolean[][] visited = new boolean[51][51];
        Queue<Cell> queue = new LinkedList<>();
        queue.add(target);
        while (!queue.isEmpty()) {
            Cell cell = queue.poll();
            cell.value = value;
            visited[cell.r][cell.c] = true;
            for (Cell m : cell.merges) {
                if (!visited[m.r][m.c]) {
                    queue.add(m);
                }
            }
        }
    }
}