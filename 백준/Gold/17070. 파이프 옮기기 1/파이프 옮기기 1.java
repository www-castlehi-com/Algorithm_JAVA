import java.util.*;
import java.io.*;

public class Main
{
    
    private static int n;
    private static int[][] map;
    private static int answer = 0;
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
		    map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		map[0][0] = 2;
		map[0][1] = 2;
		
		connect(0, 1);
		
		System.out.print(answer);
		br.close();
	}
	
	private static void connect(int y, int x) {
	    if (y == n - 1 && x == n - 1) {
	        answer++;
	        return ;
	    }
	    
	    if (map[y][x] == 2) {
	        moveHorizontal(y, x);
	        moveDiagonal(y, x);
	    } else if (map[y][x] == 3) {
	        moveVertical(y, x);
	        moveDiagonal(y, x);
	    } else if (map[y][x] == 4) {
	        moveHorizontal(y, x);
	        moveVertical(y, x);
	        moveDiagonal(y, x);
	    }
	}
	
	private static void moveHorizontal(int y, int x) {
	    if (x + 1 < n && map[y][x + 1] == 0) {
            map[y][x + 1] = 2;
            connect(y, x + 1);
            map[y][x + 1] = 0;
        }
	}
	
	private static void moveVertical(int y, int x) {
	    if (y + 1 < n && map[y + 1][x] == 0) {
            map[y + 1][x] = 3;
            connect(y + 1, x);
            map[y + 1][x] = 0;
        }
	}
	
	private static void moveDiagonal(int y, int x) {
	    if (x + 1 < n && y + 1 < n && map[y][x + 1] == 0 && map[y + 1][x] == 0 && map[y + 1][x + 1] == 0) {
            map[y + 1][x + 1] = 4;
            connect(y + 1, x + 1);
            map[y + 1][x + 1] = 0;
        }
	}
}