package Baekjoon.Class;

import java.io.*;
import java.util.PriorityQueue;

public class P_5639 {

    static class Node {
        int elem;
        Node left, right;

        public Node(int elem, Node left, Node right) {
            this.elem = elem;
            this.left = left;
            this.right = right;
        }
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node root = new Node(n, null, null);
        while (true) {
            String s = br.readLine();
            if (s == null || s.equals("")) break;

            addNode(root, Integer.parseInt(s));
        }

        postorderTraversal(root);

        bw.flush();
    }

    private static void postorderTraversal(Node root) throws IOException {
        if (root.left != null) postorderTraversal(root.left);
        if (root.right != null) postorderTraversal(root.right);
        bw.write(root.elem + "\n");
    }

    private static void addNode(Node root, int n) {
        if (root.elem < n) {
            if (root.right != null) addNode(root.right, n);
            else root.right = new Node(n, null, null);
        }
        else {
            if (root.left != null) addNode(root.left, n);
            else root.left = new Node(n, null, null);
        }
    }
}
