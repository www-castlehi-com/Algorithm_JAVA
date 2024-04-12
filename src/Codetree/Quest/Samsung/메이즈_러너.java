package Codetree.Quest.Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 메이즈_러너 {
	
	static final int MAX_DIST = 200;
	
	static int n, m, k;
	static int[][] map;
	
	static List<Player> players = new ArrayList<>();
	static int moveDist = 0;
	
	static private class Player implements Comparable<Player> {
		int r, c;

		public Player(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int compareTo(Player o) {
			Player p = (Player) o;
			
			int thisDist = calcDistWithExit(r, c);
			int pDist = calcDistWithExit(p.r, p.c);
			
			if (thisDist == pDist) {
				if (this.r == p.r) {
					return this.c - p.c;
				}
				
				return this.r - p.r;
			}
			
			return thisDist - pDist;
		}
	}
	
	static int er, ec;
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	private static void initialize() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			players.add(new Player(r, c));
		}
		
		st = new StringTokenizer(br.readLine());
		er = Integer.parseInt(st.nextToken());
		ec = Integer.parseInt(st.nextToken());
	}
	
	private static void print() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(moveDist).append("\n");
		sb.append(er).append(" ").append(ec);
		
		System.out.print(sb);
	}
	
	public static void main(String[] args) throws IOException {
		initialize();
		
		while (k-- > 0 && !players.isEmpty()) {
			
			for (int i = 0; i < players.size(); i++) {
				boolean isExit = movePlayer(players.get(i));
				
				if (isExit) {
					i--;
				}
			}
			
			if (players.isEmpty()) {
				break;
			}
			
			rotateMaze();
		}
		
		print();
	}
	
	private static boolean movePlayer(Player player) {
		int pr = player.r;
		int pc = player.c;
		boolean isMove = false;
		
		int curDist = calcDistWithExit(pr, pc);
		for (int i = 0; i < 4; i++) {
			int nextPr = player.r + dr[i];
			int nextPc = player.c + dc[i];
			
			if (nextPr >= 1 && nextPr <= n && nextPc >= 1 && nextPc <= n && map[nextPr][nextPc] == 0) {
				int subDist = calcDistWithExit(nextPr, nextPc);
				
				if (subDist < curDist) {
					pr = nextPr;
					pc = nextPc;
					curDist = subDist;
					isMove = true;
				}
			}
		}
		
		player.r = pr;
		player.c = pc;
		moveDist += (isMove ? 1 : 0);
		
		return isMove ? checkExit(player) : false;
	}
	
	private static boolean checkExit(Player player) {
		if (player.r == er && player.c == ec) {
			players.remove(player);
			return true;
		}
		
		return false;
	}
	
	private static void rotateMaze() {
		int[] box = {1, 1};
		int boxSize = 1;
		
		Loop:
		for (boxSize = 2; boxSize <= n; boxSize++) {
			for (int i = 1; i <= n - boxSize + 1; i++) {
				for (int j = 1; j <= n - boxSize + 1; j++) {
					if (isIncludeObject(er, ec, i, j, boxSize)) {
						for (Player player : players) {
							if (isIncludeObject(player.r, player.c, i, j, boxSize)) {
								box[0] = i;
								box[1] = j;
								break Loop;
							}
						}
					}
				}
			}
		}
		
		rotateBox(box, boxSize);
	}
	
	private static void rotateBox(int[] box, int boxSize) {
		int r = box[0];
		int c = box[1];
		
		int[][] targetMap = new int[boxSize + 1][boxSize + 1];
		for (int i = r; i < r + boxSize; i++) {
			for (int j = c; j < c + boxSize; j++) {
				targetMap[i - r + 1][j - c + 1] = map[i][j];
			}
		}
		
		int[][] copyMap = new int[boxSize + 1][boxSize + 1];
		for (int i = 1; i <= boxSize; i++) {
			for (int j = 1; j<= boxSize; j++) {
				copyMap[j][boxSize - i + 1] = targetMap[i][j];
			}
		}
		
		for (int i = r; i < r + boxSize; i++) {
			for (int j = c; j < c + boxSize; j++) {
				map[i][j] = (copyMap[i - r + 1][j - c + 1] == 0) ? 0 : copyMap[i - r + 1][j - c + 1] - 1;
			}
		}
		
		rotateExit(boxSize, r - 1, c - 1);
		for (Player player : players) {
			if (isIncludeObject(player.r, player.c, r, c, boxSize)) {
				rotatePlayer(player, boxSize, r - 1, c - 1);
			}
		}
	}
	
	private static void rotateExit(int boxSize, int dR, int dC) {
		er -= dR;
		ec -= dC;
		
		int tmpR = er;
		er = ec;
		ec = boxSize - tmpR + 1;
		
		er += dR;
		ec += dC;
	}
	
	private static void rotatePlayer(Player player, int boxSize, int dR, int dC) {
		player.r -= dR;
		player.c -= dC;
		
		int tmpR = player.r;
		player.r = player.c;
		player.c = boxSize - tmpR + 1;
		
		player.r += dR;
		player.c += dC;
	}
	
	private static int calcDistWithExit(int r, int c) {
		return Math.abs(r - er) + Math.abs(c - ec);
	}
	
	private static boolean isIncludeObject(int tr, int tc, int r, int c, int boxSize) {
		return (tr >= r && tr < r + boxSize && tc >= c && tc < c + boxSize);
	}
}