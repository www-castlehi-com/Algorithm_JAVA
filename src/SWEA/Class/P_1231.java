package SWEA.Class;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1231 {

	static class Node {
		int idx;
		String word;
		Node left;
		Node right;

		public Node(int idx, String word, Node left, Node right) {
			this.idx = idx;
			this.word = word;
			this.left = left;
			this.right = right;
		}
	}

	static int n;
	static Node[] tree = new Node[101];
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 1; i <= 100; i++) {
			tree[i] = new Node(i, null, null, null);
		}

		for (int test = 1; test <= 10; test++) {
			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				String word = st.nextToken();
				int left = 0;
				int right = 0;

				if (st.hasMoreTokens()) {
					left = Integer.parseInt(st.nextToken());
				}
				if (st.hasMoreTokens()) {
					right = Integer.parseInt(st.nextToken());
				}

				tree[idx].word = word;
				tree[idx].left = tree[left];
				tree[idx].right = tree[right];
			}

			sb.append("#").append(test).append(" ");
			inOrder(1);
			sb.append("\n");
		}

		System.out.print(sb);
	}

	private static void inOrder(int idx) {
		if (tree[idx].left != null) {
			inOrder(tree[idx].left.idx);
		}
		sb.append(tree[idx].word);
		if (tree[idx].right != null) {
			inOrder(tree[idx].right.idx);
		}
	}
}
