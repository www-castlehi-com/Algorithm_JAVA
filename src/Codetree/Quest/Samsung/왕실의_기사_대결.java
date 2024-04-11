package Codetree.Quest.Samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 왕실의_기사_대결 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int l, n, q;
	static int[][] map;
	static Soldier[] soldiers;
	
	private static class Soldier {
		int r, c;
		int h, w;
		int k;
		int damage;
		boolean isDead;
		
		public Soldier(int r, int c, int h, int w, int k, int damage, boolean isDead) {
			this.r = r;
			this.c = c;
			this.h = h;
			this.w = w;
			this.k = k;
			this.damage = damage;
			this.isDead = isDead;
		}
	}
	
	static int[] dc = {0, 1, 0, -1};
	static int[] dr = {-1, 0, 1, 0};
	
	private static void initialize() throws IOException {
		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		map = new int[l + 1][l + 1];
		for (int i = 1; i <= l; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= l; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		soldiers = new Soldier[n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			Soldier soldier = new Soldier(r, c, h, w, k, 0, false);
			
			soldiers[i] = soldier;
		}
	}
	
	private static void print() {
		int sum = 0;
		for (int i = 1; i <= n; i++) {
			if (!soldiers[i].isDead) {
				sum += soldiers[i].damage;
			}
		}
		
		System.out.print(sum);
	}
	
	public static void main(String[] args) throws IOException {
		initialize();
		
		for (int j = 0; j < q; j++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			if (soldiers[i].isDead) {
				continue;
			}
			
			boolean canMove = checkMove(i, d);
			if (canMove) {
				move(i, d, false);
			}
		}
		
		print();
	}
	
	private static boolean checkMove(int i, int d) {
		Soldier soldier = soldiers[i];
		int nextR = soldier.r + dr[d];
		int nextC = soldier.c + dc[d];
		
		if (canMove(nextR, nextC, soldier.h, soldier.w)) {
			List<Integer> targetSoldiers = findPushSoldiers(i, nextR, nextC, soldier.h, soldier.w);
			
			boolean canPush = true;
			for (Integer idx : targetSoldiers) {
				canPush = checkMove(idx, d);
				
				if (!canPush) {
					return false;
				}
			}
			
			return true;
		}
		return false;
	}
	
	private static void move(int i, int d, boolean isTarget) {
		Soldier soldier = soldiers[i];
		int nextR = soldier.r + dr[d];
		int nextC = soldier.c + dc[d];
		
		List<Integer> targetSoldiers = findPushSoldiers(i, nextR, nextC, soldier.h, soldier.w);
		
		for (Integer idx : targetSoldiers) {
			move(idx, d, true);
		}
		
		soldier.r = nextR;
		soldier.c = nextC;
		if (isTarget) {
			int hp = 0;
			for (int j = soldier.r; j < soldier.r + soldier.h; j++) {
				for (int k = soldier.c; k < soldier.c + soldier.w ; k++) {
					if (map[j][k] == 1) {
						hp++;
					}
				}
			}
			
			soldier.damage += hp;
			soldier.k -= hp;
			if (soldier.k <= 0) {
				soldier.isDead = true;
			}
		}
	}
	
	private static List<Integer> findPushSoldiers(int i, int r, int c, int h, int w) {
		List<Integer> targetSoldiers = new ArrayList<Integer>();
		
		for (int j = 1; j <= n; j++) {
			if (j == i) {
				continue;
			}
			
			Soldier target = soldiers[j];
			
			if (target.isDead) {
				continue;
			}
			
			Loop:
			for (int k = r; k < r + h; k++) {
				for (int m = c; m < c + w; m++) {
					if (checkRange(k, m, target.r, target.r + target.h - 1, target.c, target.c + target.w - 1)) {
						targetSoldiers.add(j);
						break Loop;
					}
				}
			}
		}
		
		return targetSoldiers;
	}
	
	private static boolean canMove(int r, int c, int h, int w) {
		if (checkRange(r, c, 1, l, 1, l) && checkRange(r + h - 1, c + w - 1, 1, l, 1, l) && !checkWall(r, c, h, w)) {
			return true;
		}
		return false;
	}
	
	private static boolean checkRange(int r, int c, int rs, int re, int cs, int ce) {
		if (r >= rs && r <= re && c >= cs && c <= ce) {
			return true;
		}
		return false;
	}
	
	private static boolean checkWall(int r, int c, int h, int w) {
		for (int i = r; i < r + h; i++) {
			for (int j = c; j < c + w; j++) {
				if (map[i][j] == 2) {
					return true;
				}
			}
		}
		return false;
	}
}
