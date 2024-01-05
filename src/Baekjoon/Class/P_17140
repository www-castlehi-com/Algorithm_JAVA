package Baekjoon.Class;

import java.io.*;
import java.util.*;

public class P_17140
{
    static int[][] arr = new int[101][101];
    static int yLength = 3;
    static int xLength = 3;
    
    public static class Number implements Comparable<Number> {
        int num;
        int cnt;
        
        Number(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Number n) {
            if (this.num == 0 || n.num == 0) {
                if (this.num == 0 && n.num == 0) {
                    return 0;
                }
                return this.num == 0 ? 1 : -1;
            } 
            if (this.cnt == n.cnt) return this.num - n.num;
            else return this.cnt - n.cnt;
        }
    }
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		for (int i = 0; i < 3; i++) {
		    st = new StringTokenizer(br.readLine(), " ");
		    for (int j = 0; j < 3; j++) {
		        arr[i][j] = Integer.parseInt(st.nextToken());
		    }
		}
		
		int time = 0;
		while (!isEnd(r, c, k)) {
		    if (time > 100) {
		        time = -1;
		        break;
		    }
		    
		    boolean nextCalculationIsColumn = getNextCalculationIsColumn();
		    if (nextCalculationIsColumn) {
		        calculateColumn();
		    }
		    else {
		        calculateRow();
		    }
		    time++;
		}
		System.out.println(time);
	}
	
	public static boolean isEnd(int r, int c, int k) {
	    return arr[r][c] == k;
	}
	
	public static boolean getNextCalculationIsColumn() {
	    if (xLength > yLength) return false;
	    else return true;
	}
	
	public static void calculateColumn() {
	    int[][] arrCopy = new int[101][101];
	    int newXLength = 0;
	    for (int i = 0; i < yLength; i++) {
	        HashMap<Integer, Integer> map = new HashMap<>();
	        for (int j = 0; j < xLength; j++) {
	            if (arr[i][j] == 0) continue;
	            map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
	        }
	        
	        ArrayList<Number> newNumber = new ArrayList<>();
	        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
	            newNumber.add(new Number(entry.getKey(), entry.getValue()));
	        }
	        newXLength = Math.max(newXLength, newNumber.size() * 2);
	        Collections.sort(newNumber);
	        
	        for (int j = 0; j < newNumber.size(); j++) {
	            if (j >= 50) break;
	            arrCopy[i][2 * j] = newNumber.get(j).num;
	            arrCopy[i][2 * j + 1] = newNumber.get(j).cnt;
	        }
	    }
	    xLength = Math.min(100, newXLength);
	    arr = arrCopy;
	}
	
	public static void calculateRow() {
	    int[][] arrCopy = new int[101][101];
	    int newYLength = 0;
	    for (int i = 0; i < xLength; i++) {
	        HashMap<Integer, Integer> map = new HashMap<>();
	        for (int j = 0; j < yLength; j++) {
	            if (arr[j][i] == 0) continue;
	            map.put(arr[j][i], map.getOrDefault(arr[j][i], 0) + 1);
	        }
	        
	        ArrayList<Number> newNumber = new ArrayList<>();
	        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
	            newNumber.add(new Number(entry.getKey(), entry.getValue()));
	        }
	        newYLength = Math.max(newYLength, newNumber.size() * 2);
	        Collections.sort(newNumber);
	        for (int j = 0; j < newNumber.size(); j++) {
	            if (j >= 50) break;
	            arrCopy[2 * j][i] = newNumber.get(j).num;
	            arrCopy[2 * j + 1][i] = newNumber.get(j).cnt;
	        }
	    }
	    yLength = Math.min(100, newYLength);
	    arr = arrCopy;
	}
}
