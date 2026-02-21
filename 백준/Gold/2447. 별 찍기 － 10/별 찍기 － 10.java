import java.io.*;
import java.util.*;

public class Main
{
    
    private static char[][] pattern;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		pattern = new char[n][n];
		
		drawPattern(0, 0, n, false);
		
		printPattern(bw);
	}
	
	private static void drawPattern(int y, int x, int n, boolean isBlank) {
	    if (isBlank) {
	        for (int i = y; i < y + n; i++) {
	            for (int j = x; j < x + n; j++) {
	                pattern[i][j] = ' ';
	            }
	        }
	        return ;
	    }
	    
	    if (n == 1) {
	        pattern[y][x] = '*';
	        return;
	    }
	    
	    int divideSize = n / 3;
	    for (int i = y; i < y + n; i += divideSize) {
	        for (int j = x; j < x + n; j += divideSize) {
	            if (i == y + divideSize && j == x + divideSize) {
	                drawPattern(i, j, divideSize, true);
	            } else {
	                drawPattern(i, j, divideSize, false);
	            }
	        }
	    }
	}
	
	private static void printPattern(BufferedWriter bw) throws IOException {
	    for (int i = 0; i < pattern.length; i++) {
	        for (int j = 0; j < pattern[i].length; j++) {
	            bw.write(pattern[i][j]);
	        }
	        bw.write("\n");
	    }
	    bw.flush();
	}
}