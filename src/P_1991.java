import java.io.*;

public class P_1991 {

    static BufferedWriter bw;

    public static class Node {
        char elem;
        Node left;
        Node right;

        public Node(char elem, Node left, Node right) {
            this.elem = elem;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        Node root = new Node('A', null, null);
        for (int i = 0; i < n; i++) {
            String[] nodes = br.readLine().split(" ");
            char parent = nodes[0].charAt(0);
            char left = nodes[1].charAt(0);
            char right = nodes[2].charAt(0);

            addNode(root, parent, left, right);
        }

        PreOrderTraversal(root);
        bw.newLine();
        InOrderTraversal(root);
        bw.newLine();
        PostOrderTraversal(root);
        bw.newLine();
        bw.flush();
    }

    private static void PostOrderTraversal(Node root) throws IOException {
        if (root.left != null) PostOrderTraversal(root.left);
        if (root.right != null) PostOrderTraversal(root.right);
        bw.write(root.elem);
    }

    private static void InOrderTraversal(Node root) throws IOException {
        if (root.left != null) InOrderTraversal(root.left);
        bw.write(root.elem);
        if (root.right != null) InOrderTraversal(root.right);
    }

    private static void addNode(Node root, char parent, char left, char right) {
        if (root.elem == parent) {
            if (left != '.') root.left = new Node(left, null, null);
            if (right != '.') root.right = new Node(right, null, null);
        }
        else {
            if (root.left != null) addNode(root.left, parent, left, right);
            if (root.right != null) addNode(root.right, parent, left, right);
        }
    }

    public static void PreOrderTraversal(Node root) throws IOException {
        bw.write(root.elem);
        if (root.left != null) PreOrderTraversal(root.left);
        if (root.right != null) PreOrderTraversal(root.right);
    }
}
