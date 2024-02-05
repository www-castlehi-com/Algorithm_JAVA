package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1248_2 {

    static int v, e, targetV1, targetV2;
    static Node[] tree = new Node[10001];
    static StringBuilder sb = new StringBuilder();
    static int cnt;

    static class Node {
        int v;
        Node parent;
        Node left;
        Node right;

        public Node(int v, Node parent, Node left, Node right) {
            this.v = v;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 1; i <= 10000; i++) {
            tree[i] = new Node(i, null, null, null);
        }

        int tc = Integer.parseInt(br.readLine());
        for (int test = 1; test <= tc; test++) {
            st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            targetV1 = Integer.parseInt(st.nextToken());
            targetV2 = Integer.parseInt(st.nextToken());

            for (int i = 1; i <= v; i++) {
                tree[i].parent = null;
                tree[i].left = null;
                tree[i].right = null;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < e; i++) {
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());

                Node parent = tree[v1];
                tree[v2].parent = parent;
                if (parent.left == null) {
                    parent.left = tree[v2];
                }
                else {
                    parent.right = tree[v2];
                }
            }

            Node target = tree[findParent()];
            cnt = 0;
            inOrder(target.v);

            sb.append("#").append(test).append(" ").append(target.v).append(" ").append(cnt).append("\n");
        }

        System.out.print(sb);
    }

    private static void inOrder(int v) {
        if (tree[v].left != null) {
            inOrder(tree[v].left.v);
        }
        cnt++;
        if (tree[v].right != null) {
            inOrder(tree[v].right.v);
        }
    }

    private static int findParent() {
        boolean[] targetV1Parent = new boolean[10001];

        Node ptr = tree[targetV1].parent;
        while (ptr.parent != null) {
            targetV1Parent[ptr.v] = true;
            ptr = ptr.parent;
        }

        ptr = tree[targetV2].parent;
        while (ptr.parent != null) {
            if (targetV1Parent[ptr.v]) {
                break;
            }
            ptr = ptr.parent;
        }

        return ptr.v;
    }
}
