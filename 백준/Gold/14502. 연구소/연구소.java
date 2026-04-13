import java.io.*;
import java.util.*;

public class Main
{
    
    private static int n;
    private static int m;
    private static int[][] map;
    private static List<int[]> safeZones = new ArrayList<>();
    private static List<int[]> viruses = new ArrayList<>();
    private static int answer = 0;
    
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
		    map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		    for (int j = 0; j < m; j++) {
		        if (map[i][j] == 0) {
		            safeZones.add(new int[]{i, j});
		        }
		        else if (map[i][j] == 2) {
		            viruses.add(new int[]{i, j});
		        }
		    }
		}
		
		makeWallAndProgress();
		
		System.out.print(answer);
	}
	
	private static void makeWallAndProgress() {
	    for (int i = 0; i < safeZones.size() - 2; i++) {
	        for (int j = i + 1; j < safeZones.size() - 1; j++) {
	            for (int k = j + 1; k < safeZones.size(); k++) {
	                int[][] copyMap = cloneMap();
	                makeWall(copyMap, i, j, k);
	                bfs(copyMap);
	            }
	        }
	    }
	}
	
	private static int[][] cloneMap() {
	    int[][] copyMap = new int[n][m];
	    for (int i = 0; i < n; i++) {
	        copyMap[i] = map[i].clone();
	    }
	    return copyMap;
	}
	
	private static void bfs(int[][] copyMap) {
	    Queue<int[]> queue = new LinkedList<>();
	    for (int[] virus : viruses) {
	        queue.add(virus);
	    }
	    
	    while (!queue.isEmpty()) {
	        int[] virus = queue.poll();
	        
	        for (int i = 0; i < 4; i++) {
	            int nextY = virus[0] + dy[i];
	            int nextX = virus[1] + dx[i];
	            
	            if (nextY >= 0 && nextY < n && nextX >= 0 && nextX < m && copyMap[nextY][nextX] == 0) {
	                copyMap[nextY][nextX] = 2;
	                queue.add(new int[]{nextY, nextX});
	            }
	        }
	    }
	    
	    int safeZoneCount = getSafeZoneCount(copyMap);
	    answer = Math.max(answer, safeZoneCount);
	}
	
	private static void makeWall(int[][] copyMap, int wall1, int wall2, int wall3) {
	    copyMap[safeZones.get(wall1)[0]][safeZones.get(wall1)[1]] = 1;
        copyMap[safeZones.get(wall2)[0]][safeZones.get(wall2)[1]] = 1;
        copyMap[safeZones.get(wall3)[0]][safeZones.get(wall3)[1]] = 1;
	}
	
	private static int getSafeZoneCount(int[][] copyMap) {
	    int count = 0;
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < m; j++) {
	            if (copyMap[i][j] == 0) {
	                count++;
	            }
	        }
	    }
	    return count;
	}
}