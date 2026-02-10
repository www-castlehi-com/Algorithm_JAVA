package kakao;

import java.util.*;

public class Move {
	static int N, M;
	static int[][] board;
	static Map<Integer, App> apps;
	static int[] dy = {0, 0, 1, 0, -1}; // 1:오, 2:아, 3:왼, 4:위
	static int[] dx = {0, 1, 0, -1, 0};

	static class App {
		int id, y1, x1, y2, x2;
		App(int id, int y1, int x1, int y2, int x2) {
			this.id = id; this.y1 = y1; this.x1 = x1; this.y2 = y2; this.x2 = x2;
		}
		void move(int dir) {
			y1 += dy[dir];
			y2 += dy[dir];
			x1 += dx[dir];
			x2 += dx[dir];
		}
		boolean outOfBound() {
			return (y1 < 0 || x1 < 0 || y2 >= N || x2 >= M);
		}
	}

	public static int[][] solution(int[][] inputBoard, int[][] commands) {
		N = inputBoard.length;
		M = inputBoard[0].length;
		board = inputBoard;
		apps = new HashMap<>();

		// 앱 위치 초기화
		Map<Integer, int[]> tmp = new HashMap<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int id = board[i][j];
				if (id == 0) continue;
				tmp.putIfAbsent(id, new int[]{i, j, i, j});
				int[] cur = tmp.get(id);
				cur[0] = Math.min(cur[0], i);
				cur[1] = Math.min(cur[1], j);
				cur[2] = Math.max(cur[2], i);
				cur[3] = Math.max(cur[3], j);
			}
		}
		for (var e : tmp.entrySet()) {
			int id = e.getKey();
			int[] v = e.getValue();
			apps.put(id, new App(id, v[0], v[1], v[2], v[3]));
		}

		// 명령 실행
		for (int[] cmd : commands) {
			moveCommand(cmd[0], cmd[1]);
		}

		// 결과 보드 재구성
		int[][] result = new int[N][M];
		for (App a : apps.values()) {
			for (int y = a.y1; y <= a.y2; y++) {
				for (int x = a.x1; x <= a.x2; x++) {
					result[y][x] = a.id;
				}
			}
		}
		return result;
	}

	static void moveCommand(int startId, int dir) {
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> inQueue = new HashSet<>();
		q.add(startId);
		inQueue.add(startId);

		// 🔁 연쇄 이동 BFS
		while (!q.isEmpty()) {
			int id = q.poll();
			App app = apps.get(id);
			if (app == null) continue;

			// 이동 후 충돌할 앱 탐색
			for (App other : apps.values()) {
				if (other.id == id) continue;
				if (willCollide(app, other, dir)) {
					if (!inQueue.contains(other.id)) {
						q.add(other.id);
						inQueue.add(other.id);
					}
				}
			}

			// 실제 이동
			app.move(dir);

			// 벽 충돌 시 반대편 이동
			if (app.outOfBound()) {
				teleport(app, dir, q, inQueue);
			}
		}
	}

	static boolean willCollide(App a, App b, int dir) {
		int ay1 = a.y1 + dy[dir], ay2 = a.y2 + dy[dir];
		int ax1 = a.x1 + dx[dir], ax2 = a.x2 + dx[dir];
		return !(ay2 < b.y1 || b.y2 < ay1 || ax2 < b.x1 || b.x2 < ax1);
	}

	static void teleport(App a, int dir, Queue<Integer> q, Set<Integer> inQueue) {
		int rdir = reverseDir(dir);
		// 반대쪽으로 완전히 되돌릴 때까지 이동
		do {
			a.move(rdir);
		} while (a.outOfBound());

		// 반대편에서 겹치는 앱도 같은 방향으로 밀림
		for (App other : apps.values()) {
			if (other.id == a.id) continue;
			if (!(a.y2 < other.y1 || other.y2 < a.y1 || a.x2 < other.x1 || other.x2 < a.x1)) continue;
			// 실제로 겹친 경우
			if (!inQueue.contains(other.id)) {
				q.add(other.id);
				inQueue.add(other.id);
			}
		}
	}

	static int reverseDir(int dir) {
		if (dir == 1) return 3;
		if (dir == 3) return 1;
		if (dir == 2) return 4;
		return 2;
	}

	// ====== 디버깅용 ======
	static void print(int[][] res) {
		for (int[] r : res) {
			System.out.println(Arrays.toString(r));
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// 예시 테스트
		int[][] board = {
				{0, 9, 1, 1, 6, 0, 0, 0},
				{2, 2, 1, 1, 0, 0, 0, 0},
				{2, 2, 3, 4, 4, 4, 0, 0},
				{5, 0, 0, 4, 4, 4, 7, 0},
				{0, 0, 0, 4, 4, 4, 8, 8},
				{0, 0, 0, 0, 0, 0, 8, 8},
		};
		int[][] cmds = {{2, 1}, {3,1},{9, 2},{4, 1}};
		int[][] result = solution(board, cmds);
		print(result);
	}
}

