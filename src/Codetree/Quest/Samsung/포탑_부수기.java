package Codetree.Quest.Samsung;

import java.io.*;
import java.util.*;

public class 포탑_부수기 {
	
	static int n, m, k;
	static int turn = 1;
	static int[][] map;
	
	static Turret[] turrets;
	static List<Turret> liveTurrets;
	
	static class Turret implements Comparable<Turret>{
		int r, c;
		int power;
		int attackTurn;
		boolean isAttacked;
		
		public Turret(int r, int c, int power, int attackTurn, boolean isAttacked) {
			this.r = r;
			this.c = c;
			this.power = power;
			this.attackTurn = attackTurn;
			this.isAttacked = isAttacked;
		}

		@Override
		public int compareTo(Turret o) {
			Turret t = (Turret) o;
			
			if (this.power == t.power) {
				if (this.attackTurn == t.attackTurn) {
					if (this.r + this.c == t.r + t.c) {
						return t.c - this.c;
					}
					
					return (t.r + t.c) - (this.r + this.c);
				}
				
				return t.attackTurn - this.attackTurn;
			}
			
			return this.power - t.power;
		}
	}
	
	static int[] dr = {0, 1, 0, -1, 1, 1, -1, -1};
	static int[] dc = {1, 0, -1, 0, 1, -1, 1, -1};
	
	static class Way {
		int r, c;
		
		public Way(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	private static void initialize() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int idx = 1;
		turrets = new Turret[n * m + 1];
		liveTurrets = new ArrayList<>();
		
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j< m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0) {
					Turret turret = new Turret(i, j, map[i][j], 0, false);
					turrets[idx] = turret;
					liveTurrets.add(turret);
					map[i][j] = idx++;
				}
			}
		}		
	}
	
	private static void print() {
		Collections.sort(liveTurrets);
		
		System.out.print(liveTurrets.get(liveTurrets.size() - 1).power);
	}
	
	public static void main(String[] args) throws IOException {
		initialize();
		
		while (turn <= k && liveTurrets.size() != 1) {
			Collections.sort(liveTurrets);
			
			Turret attacker = liveTurrets.get(0);
			Turret attacked = liveTurrets.get(liveTurrets.size() - 1);
			
			attack(attacker, attacked);
			
			setting();
			
			turn++;
		}
		
		print();
	}
	
	private static void attack(Turret attacker, Turret attacked) {
		attacker.power += (n + m);
		attacker.attackTurn = turn;
		
		attacked.isAttacked = true;
		attacked.power -= attacker.power;
		
		Way[][] priorityMap = findShortestWay(attacker.r, attacker.c, attacked.r, attacked.c);
		boolean canLaserAttack = !(priorityMap == null);
		
		if (canLaserAttack) {
			laserAttack(attacker, attacked, priorityMap);
		}
		else {
			bombAttack(attacker, attacked);
		}
	}
	
	private static void laserAttack(Turret attacker, Turret attacked, Way[][] priorityMap) {	
		int curR = attacked.r;
		int curC = attacked.c;

		while (true) {
			Way nextWay = priorityMap[curR][curC];
			
			if (nextWay.r == attacker.r && nextWay.c == attacker.c) {
				break;
			}
			
			int idx = map[nextWay.r][nextWay.c];
			turrets[idx].power -= (attacker.power / 2);
			turrets[idx].isAttacked = true;
			
			curR = nextWay.r;
			curC = nextWay.c;
		}
	}
	
	private static void bombAttack(Turret attacker, Turret attacked) {
		int curR = attacked.r;
		int curC = attacked.c;
	
		for (int i = 0; i < 8; i++) {
			int nextR = (curR + dr[i] + n) % n;
			int nextC = (curC + dc[i] + m) % m;
			
			if (map[nextR][nextC] > 0 && !(nextR == attacker.r && nextC == attacker.c)) {
				int idx = map[nextR][nextC];
				turrets[idx].isAttacked = true;
				turrets[idx].power -= (attacker.power / 2);
			}
		}
	}
	
	private static void setting() {
		for (int i = 0; i< n; i++) {
			for (int j = 0; j< m; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				
				int idx = map[i][j];
				if (turrets[idx].attackTurn == turn) {
					continue;
				}
				
				if (turrets[idx].isAttacked) {
					turrets[idx].isAttacked = false;
					if (turrets[idx].power <= 0) {
						liveTurrets.remove(turrets[idx]);
						map[i][j] = 0;
					}
					continue;
				}
				
				turrets[idx].power++;
			}
		}
	}
	
	private static Way[][] findShortestWay(int sr, int sc, int er, int ec) {
		LinkedList<Way> queue = new LinkedList<>(); 
		boolean[][] visited = new boolean[n][m];
		Way[][] prevMap = new Way[n][m];
		
		queue.add(new Way(sr, sc));
		visited[sr][sc] = true;
		
		while (!queue.isEmpty()) {
			Way poll = queue.poll();
			
			int curR = poll.r;
			int curC = poll.c;
			
			if (curR == er && curC == ec) {
				return prevMap;
			}
			
			for (int i = 0; i < 4; i++) {
				int nextR = (curR + dr[i] + n) % n;
				int nextC = (curC + dc[i] + m) % m;
				
				if (!visited[nextR][nextC] && map[nextR][nextC] != 0) {
					visited[nextR][nextC] = true;
					prevMap[nextR][nextC] = poll;
					queue.add(new Way(nextR, nextC));
				}
			}
		}
		
		return null;
	}
}
