package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_1248 {

    static int v, e;
    static int v1, v2;
    static Node[] nodes;

    static public class Node {
        int element;
        Node parent;
        Node leftChild;
        Node rightChild;

        public Node(int element, Node parent, Node leftChild, Node rightChild) {
            this.element = element;
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        for (int test = 1; test <= t; test++) {
            st = new StringTokenizer(br.readLine(), " ");
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            v1 = Integer.parseInt(st.nextToken());
            v2 = Integer.parseInt(st.nextToken());
            nodes = new Node[v + 1];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < e; i++) {
                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                if (nodes[parent] == null) {
                    nodes[parent] = new Node(parent, null, null, null);
                }
                if (nodes[child] == null) {
                    nodes[child] = new Node(child, nodes[parent], null, null);
                }
                else {
                    nodes[child].parent = nodes[parent];
                }

                if (nodes[parent].leftChild == null) {
                    nodes[parent].leftChild = nodes[child];
                }
                else {
                    nodes[parent].rightChild = nodes[child];
                }

            }

            boolean[] visited = new boolean[v + 1];
            getParents(nodes[v1], visited);

            Node nearParent = findNearParent(nodes[v2], visited);
            int cnt = findChildrenCnt(nearParent);

            System.out.println("#" + test + " " + nearParent.element + " " + cnt);
        }
    }

    private static int findChildrenCnt(Node nearParent) {
        int cnt = 1;
        if (nearParent.leftChild != null) {
            cnt += findChildrenCnt(nearParent.leftChild);
        }
        if (nearParent.rightChild != null) {
            cnt += findChildrenCnt(nearParent.rightChild);
        }
        return cnt;
    }

    private static Node findNearParent(Node target, boolean[] visited) {
        Node parent = nodes[1];
        for (Node x = target; x != null; x = x.parent) {
            if (visited[x.element]) {
                parent = x;
                break;
            }
        }
        return parent;
    }

    private static void getParents(Node target, boolean[] visited) {
        for (Node x = target; x != null; x = x.parent) {
            visited[x.element] = true;
        }
    }
}
