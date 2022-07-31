package DXAlgorithm.review;

import java.sql.Array;
import java.util.*;

class SWEA_9429 {

    static class UserSolution {

        static class Node {
            String name;
            ArrayList< Node > children;
            Node parent;
            int count = 0;

            public Node() {
                name = "/";
                children = new ArrayList<>();
            }

            public Node(String name) {
                this.name = name;
                children = new ArrayList<>();
            }

            public Node(String name, ArrayList<Node> children, Node parent, int count) {
                this.name = name;
                this.children = children;
                this.parent = parent;
                this.count = count;
            }

            public Node(Node node) {
                this.name = node.name;
                this.children = node.children;
                this.parent = node.parent;
                this.count = node.count;
            }
        }

        Node root;

        Node get_dir(String path) {
            if (path.equals("/")) return this.root;
            String[] sPath = path.substring(1, path.length()).split("/");

            Node cur = root;
            for (int i = 0; i < sPath.length; i++) {
                for (Node child : cur.children) {
                    if (child.name.equals(sPath[i])) {
                        cur = child;
                        break;
                    }
                }
            }
            return cur;
        }

        void init(int n) {
            root = new Node();
        }

        void cmd_mkdir(char[] path, char[] name) {
            String sName = new String(name, 0, name.length - 1);
            String sPath = new String(path, 0, path.length - 1);

            Node newNode = new Node(sName, new ArrayList<Node>(), null, 0);

            Node target = get_dir(sPath);
            target.children.add(newNode);
            newNode.parent = target;
            while (target != null) {
                target.count++;
                target = target.parent;
            }
        }

        void cmd_rm(char[] path) {
            String target = new String(path, 0, path.length - 1);

            Node rmNode = get_dir(target);
            rmNode.parent.children.remove(rmNode);

            int rmCount = rmNode.count + 1; // 삭제 될 노드의 하위 디렉토리 수를 저장한다
            Node parent = rmNode.parent; //삭제될노드의 부모를 저장

            while (parent != null) { // 부모노드를 쭉 따라 올라가며 count의 수를 조정
                parent.count -= rmCount;
                parent = parent.parent;
            }

        }

        void cmd_cp(char[] srcPath, char[] dstPath) {
            String src = new String(srcPath, 0, srcPath.length - 1);
            String dst = new String(dstPath, 0, dstPath.length - 1);

            Node srcNode = get_dir(src);
            Node dstNode = get_dir(dst);

            Node cpyNode = new Node(srcNode.name);
            cpyNode.count = srcNode.count;

            dstNode.children.add(cpyNode);

            cpyNode.parent = dstNode;
            treeCopy(srcNode, cpyNode);

            int srcCount = srcNode.count + 1;
            while (dstNode != null) {
                dstNode.count += srcCount;
                dstNode = dstNode.parent;
            }

        }

        void treeCopy(Node src, Node dst) {
            if (src.children.isEmpty())
                return;

            for (int i = 0; i < src.children.size(); i++) {
                Node cpyNode = new Node(src.children.get(i).name);
                dst.children.add(cpyNode);
                cpyNode.parent = dst;
                cpyNode.count = src.children.get(i).count;
                treeCopy(src.children.get(i), dst.children.get(i));
            }

        }

        void cmd_mv(char[] srcPath, char[] dstPath) {
            String sSrcPath = new String(srcPath, 0, srcPath.length - 1);
            String sDstPath = new String(dstPath, 0, dstPath.length - 1);

            Node src = get_dir(sSrcPath);
            Node dst = get_dir(sDstPath);

            int cnt = src.count + 1;
            Node srcParent = src.parent;
            srcParent.children.remove(src);
            while (srcParent != null) {
                srcParent.count -= cnt;
                srcParent = srcParent.parent;
            }

            src.parent = dst;
            dst.children.add(src);

            while (dst != null) {
                dst.count += cnt;
                dst = dst.parent;
            }

        }

        int cmd_find(char[] path) {
            String str = new String(path, 0, path.length - 1);

            if (path[1] == '\0') {
                return root.count;
            }
            Node node = get_dir(str);
            return node.count;
        }
    }

    private static UserSolution usersolution = new UserSolution();

    private final static int CMD_MKDIR		= 1;
    private final static int CMD_RM			= 2;
    private final static int CMD_CP			= 3;
    private final static int CMD_MV			= 4;
    private final static int CMD_FIND		= 5;

    private final static int NAME_MAXLEN	= 6;
    private final static int PATH_MAXLEN	= 1999;


    private static boolean run(Scanner sc, int m) throws Exception {

        boolean isAccepted = true;
        int cmd;
        char[] name;
        char[] path1;
        char[] path2;
        String inputStr = "";

        while (m-- > 0) {

            cmd = sc.nextInt();

            if (cmd == CMD_MKDIR) {
                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';
                inputStr = sc.next() + " ";
                name = inputStr.toCharArray();
                name[inputStr.length() - 1] = '\0';

                usersolution.cmd_mkdir(path1, name);
            }
            else if (cmd == CMD_RM) {
                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';

                usersolution.cmd_rm(path1);
            }
            else if (cmd == CMD_CP) {
                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';
                inputStr = sc.next() + " ";
                path2 = inputStr.toCharArray();
                path2[inputStr.length() - 1] = '\0';

                usersolution.cmd_cp(path1, path2);
            }
            else if (cmd == CMD_MV) {
                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';
                inputStr = sc.next() + " ";
                path2 = inputStr.toCharArray();
                path2[inputStr.length() - 1] = '\0';

                usersolution.cmd_mv(path1, path2);
            }
            else {
                int ret;
                int answer;

                inputStr = sc.next() + " ";
                path1 = inputStr.toCharArray();
                path1[inputStr.length() - 1] = '\0';

                ret = usersolution.cmd_find(path1);

                answer = sc.nextInt();

                isAccepted &= (ret == answer);
            }
        }

        return isAccepted;
    }

    public static void main(String[] args) throws Exception {
        int test, T;
        int n, m;

        // System.setIn(new java.io.FileInputStream("sample_input.txt"));

        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        for (test = 1; test <= T; ++test) {

            n = sc.nextInt();
            m = sc.nextInt();

            usersolution.init(n);

            if (run(sc, m)) {
                System.out.println("#" + test + " 100");
            }
            else {
                System.out.println("#" + test + " 0");
            }
        }

        sc.close();
    }
}