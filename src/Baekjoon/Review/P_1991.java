package Baekjoon.Review;

import java.io.*;

public class P_1991 {

    static class Node {
        char elem;
        Node left;
        Node right;

        public Node(char elem, Node left, Node right) {
            this.elem = elem;
            this.left = left;
            this.right = right;
        }
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node root = new Node('A', null, null);
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            char parent = line[0].charAt(0);
            char left = line[1].charAt(0);
            char right = line[2].charAt(0);
            addNode(root, parent, left, right);
        }

        preorderTraversal(root);
        bw.newLine();
        inorderTraversal(root);
        bw.newLine();
        postorderTraversal(root);

        bw.flush();
    }

    private static void postorderTraversal(Node node) throws IOException {
        if (node.left != null) postorderTraversal(node.left);
        if (node.right != null) postorderTraversal(node.right);
        bw.write(node.elem);
    }

    private static void inorderTraversal(Node node) throws IOException {
        if (node.left != null) inorderTraversal(node.left);
        bw.write(node.elem);
        if (node.right != null) inorderTraversal(node.right);
    }

    private static void preorderTraversal(Node node) throws IOException {
        bw.write(node.elem);
        if (node.left != null) preorderTraversal(node.left);
        if (node.right != null) preorderTraversal(node.right);
    }

    private static void addNode(Node root, char parent, char left, char right) {
        if (parent == root.elem) {
            if (left != '.') root.left = new Node(left, null, null);
            if (right != '.') root.right = new Node(right, null, null);
        }
        else {
            if (root.left != null) addNode(root.left, parent, left, right);
            if (root.right != null) addNode(root.right, parent, left, right);
        }
    }
}
