package kakao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Move5 {

	private class App {
		int id;
		int x1;
		int y1;
		int x2;
		int y2;
		int length;

		public App(int id, int x1, int y1, int x2, int y2) {
			this.id = id;
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
			this.length = x2 - x1 + 1;
		}
	}

	private static int[] dy = {0, 0, 1, 0, -1};
	private static int[] dx = {0, 1, 0, -1, 0};

	private int n;
	private int m;
	private int[][] answer;

	public int[][] solution(int[][] board, int[][] commands) {
		n = board.length;
		m = board[0].length;
		answer = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				answer[i][j] = board[i][j];
			}
		}

		for (int[] command : commands) {
			move(command);
		}

		return answer;
	}

	private void move(int[] command) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (answer[i][j] == command[0]) {
					moveApp(answer[i][j], command[1], new HashSet<>());
					return ;
				}
			}
		}
	}

	private void moveApp(int id, int dir, Set<Integer> moving) {
		if (moving.contains(id)) {
			return ;
		}
		moving.add(id);

		App app = findApp(id);
		int nextY1 = app.y1 + dy[dir];
		int nextY2 = app.y2 + dy[dir];
		int nextX1 = app.x1 + dx[dir];
		int nextX2 = app.x2 + dx[dir];

		if (nextY1 < 0) {
			nextY1 = n - app.length;
			nextY2 = n - 1;
		}
		if (nextY2 >= n) {
			nextY2 = app.length - 1;
			nextY1 = 0;
		}
		if (nextX1 < 0) {
			nextX1 = m - app.length;
			nextX2 = m - 1;
		}
		if (nextX2 >= m) {
			nextX2 = app.length - 1;
			nextX1 = 0;
		}

		List<Integer> blockedIds = getBlockingAppIds(app.id, nextY1, nextX1, nextY2, nextX2);
		for (int blockedId : blockedIds) {
			moveApp(blockedId, dir, moving);
		}

		clearCurrentApp(app);
		fillCurrentApp(id, nextY1, nextX1, nextY2, nextX2);
		moving.remove(id);
	}

	private App findApp(int id) {
		int y1 = n;
		int x1 = m;
		int y2 = 0;
		int x2 = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (answer[i][j] == id) {
					y1 = Math.min(y1, i);
					x1 = Math.min(x1, j);
					y2 = Math.max(y2, i);
					x2 = Math.max(x2, j);
				}
			}
		}

		return new App(id, x1, y1, x2, y2);
	}

	private List<Integer> getBlockingAppIds(int id, int y1, int x1, int y2, int x2) {
		Set<Integer> ids = new HashSet<>();
		for (int i = y1; i<= y2; i++) {
			for (int j = x1; j <= x2; j++) {
				int target = answer[i][j];
				if (target != 0 && target != id) {
					ids.add(target);
				}
			}
		}

		return new ArrayList<>(ids);
	}

	private void clearCurrentApp(App app) {
		for (int i = app.y1; i <=app.y2; i++) {
			for (int j = app.x1; j<=app.x2; j++) {
				answer[i][j] = 0;
			}
		}
	}

	private void fillCurrentApp(int id, int y1, int x1, int y2, int x2) {
		for (int i = y1; i <= y2; i++) {
			for (int j = x1; j <= x2; j++) {
				answer[i][j] = id;
			}
		}
	}
}
