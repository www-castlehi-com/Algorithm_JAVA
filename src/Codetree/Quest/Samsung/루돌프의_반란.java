package Codetree.Quest.Samsung;

import java.io.*;
import java.util.*;

public class 루돌프의_반란 {
	
	static final int MAX_DIST = 5000;
	
	static int n;
	static int m;
	static int p;
	static int c;
	static int d;
	
	static int rr, rc;
	static int rDir;
	
	static int[][] map;
	static Santa[] santas;
	static int liveSanta;
	
	static class Santa {
		int pn;
		int r, c;
		int score;
		int dir;
		int left;
		boolean isDead;
		
		public Santa(int pn, int r, int c, int score, int left, boolean isDead) {
			super();
			this.pn = pn;
			this.r = r;
			this.c = c;
			this.score = score;
			this.left = left;
			this.isDead = isDead;
		}
	}
	
	static int[] dc = {0, 1, 0, -1, -1, -1, 1, 1};
	static int[] dr = {-1, 0, 1, 0, -1, 1, -1, 1};
	
	public static void initialize() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		p = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		liveSanta = p;
		
		map = new int[n + 1][n + 1];
		santas = new Santa[p + 1];
		
		st = new StringTokenizer(br.readLine());
		rr = Integer.parseInt(st.nextToken());
		rc = Integer.parseInt(st.nextToken());
		
		for (int i = 1; i <= p; i++) {
			st = new StringTokenizer(br.readLine());
			
			int pn = Integer.parseInt(st.nextToken());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			
			Santa santa = new Santa(pn, sr, sc, 0, 0, false);
			
			map[sr][sc] = pn;
			santas[pn] = santa;
		}
	}
	
	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 1; i <= p; i++) {
			sb.append(santas[i].score).append(" ");
		}
		
		System.out.print(sb);
	}
	
	private static void print() {
		System.out.println("========================");
		System.out.println("rr :" + rr + ", rc : " + rc);
		
		System.out.println("score");
		for (int i = 1; i <= p; i++) {
			System.out.print(santas[i].score + " ");
		}
		System.out.println();
		
		System.out.println("left");
		for (int i = 1; i <= p ; i++) {
			System.out.print(santas[i].left + " ");
		}
		System.out.println();
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j<= n; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("========================\n");
	}

	public static void main(String[] args) throws IOException {
		initialize();
		
		for (int i = 1; i <= m && liveSanta != 0; i++) {
			Santa minSanta = findMinDistSanta();
			
			moveRudolph(minSanta);
			checkHit(minSanta, false);
			
			for (int j = 1; j <= p; j++) {
				if (santas[j].isDead) {
					continue;
				}
				
				if (santas[j].left != 0) {
					santas[j].left--;
					continue;
				}
				
				moveSanta(santas[j]);
				checkHit(santas[j], true);
			}
			
			for (int j = 1; j <= p; j++) {
				if (santas[j].isDead) {
					continue;
				}
				
				santas[j].score++;
			}
			
//			print();
		}
		
		printResult();
	}
	
	private static int calcDist(int or, int oc, int tr, int tc) {
		return (int) (Math.pow((or - tr), 2) + Math.pow((oc - tc), 2));
	}
	
	private static Santa findMinDistSanta() {
		Santa minSanta = null;
		int minDist = MAX_DIST + 1;
		
		for (int i = 1; i <= p; i++) {
			Santa target = santas[i];
			
			if (target.isDead) {
				continue;
			}
			
			int targetDist = calcDist(rr, rc, target.r, target.c);
			
			if (minSanta == null) {
				minSanta = target;
				minDist = targetDist;
			}
			else { 
				if (targetDist < minDist) {
					minSanta = target;
					minDist = targetDist;
				}
				else if (targetDist == minDist) {
					if (target.r > minSanta.r) {
						minSanta = target;
						minDist = targetDist;
					}
					else if (target.r == minSanta.r && target.c > minSanta.c) {
						minSanta = target;
						minDist = targetDist;
					}
				}
			}
		}
		
		return minSanta;
	}
	
	private static void moveRudolph(Santa target) {		
		int difR = target.r - rr;
		int difC = target.c - rc;
		
		int unitR = getUnitVector(difR);
		int unitC = getUnitVector(difC);
		
		for (int i = 0; i < 8; i++) {
			if (unitR == dr[i] && unitC == dc[i]) {
				rDir = i;
			}
		}
		
		rr += unitR;
		rc += unitC;
	}
	
	private static int getUnitVector(int v) {
		if (v == 0) {
			return 0;
		}
		else if (v > 0) {
			return 1;
		}
		else {
			return -1;
		}
	}
	
	private static void moveSanta(Santa target) {
		int curR = target.r;
		int curC = target.c;
		
		map[curR][curC] = 0;
		
		int minDist = calcDist(rr, rc, curR, curC);
		for (int i = 0; i < 4; i++) {
			int nextR = curR + dr[i];
			int nextC = curC + dc[i];
			
			if (nextR >= 1 && nextR <= n && nextC >= 1 && nextC <= n && map[nextR][nextC] == 0) {
				int subDist = calcDist(rr, rc, nextR, nextC);
				
				if (subDist < minDist) {
					minDist = subDist;
					target.r = nextR;
					target.c = nextC;
					target.dir = i;
				}
			}
		}
		
		map[target.r][target.c] = target.pn;
	}
	
	private static void checkHit(Santa target, boolean isSantaMove) {
		int sr = target.r;
		int sc = target.c;
		
		if (rr == sr && rc == sc) {
			int dir;
			if (!isSantaMove) {
				dir = rDir;
			}
			else {
				dir = getDir(target.dir);
			}
			
			int amount = isSantaMove ? d : c;
			hitSanta(target, dir, amount);
			
			target.score += amount;
			target.left = isSantaMove ? 1 : 2;
		}
	}
	
	private static int getDir(int dir) {		
		switch (dir) {
		case 0:
			return 2;
		case 1:
			return 3;
		case 2:
			return 0;
		case 3:
			return 1;
		}
		
		return -1;
	}
	
	private static void hitSanta(Santa target, int dir, int amount) {
		int sr = target.r;
		int sc = target.c;
		
		if (map[sr][sc] == target.pn) {
			map[sr][sc] = 0;
		}
		
		int nextR = sr + dr[dir] * amount;
		int nextC = sc + dc[dir] * amount;
		
		target.r = nextR;
		target.c = nextC;
		
		if (nextR >= 1 && nextR <= n && nextC >= 1 && nextC <= n) {
			int prev = map[nextR][nextC];
			
			map[nextR][nextC] = target.pn;
			
			if (prev != 0) {
				hitSanta(santas[prev], dir, 1);
			}
		}
		else {
			target.isDead = true;
			liveSanta--;
		}
	}
}
