package DXAlgorithm.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_SDX_Summer_2_No1 {

    static private class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static public class UserSolution {

        private final static int MAX_NODE = 10000;

        private  Node[] node = new  Node[MAX_NODE];
        private int nodeCnt = 0;
        private  Node head;

        public  Node getNode(int data) {
            node[nodeCnt] = new  Node(data);
            return node[nodeCnt++];
        }

        public void init() {
            node = new  Node[MAX_NODE];
            nodeCnt = 0;
        }

        public void addNode2Head(int data) {
             Node[] copyNode = new  Node[MAX_NODE];
            int copyNodeCnt = 0;
            copyNode[copyNodeCnt++] = new  Node(data);
            for (int i = 0; i < nodeCnt; i++) {
                copyNode[copyNodeCnt - 1].next = node[i];
                copyNode[copyNodeCnt++] = node[i];
            }
            node = copyNode;
            nodeCnt = copyNodeCnt;
            head = node[0];
        }

        public void addNode2Tail(int data) {
             Node tail = getNode(data);
            if (nodeCnt - 2 >= 0) node[nodeCnt - 2].next = tail;
        }

        public void addNode2Num(int data, int num) {
            num--;
             Node[] copyNode = new  Node[MAX_NODE];
            int copyNodeCnt = 0;
            for (int i = 0; i < num; i++) {
                copyNode[copyNodeCnt++] = node[i];
            }
            copyNode[copyNodeCnt++] = new Node(data);
            if (num - 1 >= 0) copyNode[num - 1].next = copyNode[copyNodeCnt - 1];
            for (int i = num; i < nodeCnt; i++) {
                copyNode[copyNodeCnt++] = node[i];
                if (i == num) copyNode[num].next = copyNode[copyNodeCnt - 1];
            }
            node = copyNode;
            nodeCnt = copyNodeCnt;
            head = node[0];
        }

        public void removeNode(int data) {
            int prev = Integer.MIN_VALUE;
            int next = Integer.MIN_VALUE;
            for (int i = 0; i < nodeCnt; i++) {
                if (node[i].data == data) {
                    prev = i - 1;
                    next = i + 1;
                    break;
                }
            }

            if (prev != Integer.MIN_VALUE && next != Integer.MIN_VALUE) {
                if (next >= nodeCnt) {
                    node[prev].next = null;
                }
                else if (prev < 0) {
                    for (int i = 0; i < nodeCnt - 1; i++) {
                        node[i] = node[i + 1];
                    }
                }
                else {
                    node[prev].next = node[next];
                    for (int i = prev + 1; i < nodeCnt - 1; i++) {
                        node[i] = node[i + 1];
                    }
                }
                head = node[0];
                nodeCnt--;
            }
        }

        public int getList(int[] output) {
            for (int i = 0; i < nodeCnt ;i++) {
                output[i] = node[i].data;
            }
            return nodeCnt;
        }
    }

    private final static int MAX_NODE = 10000;
    private final static int ADD_HEAD = 1;
    private final static int ADD_TAIL = 2;
    private final static int ADD_NUM = 3;
    private final static int REMOVE = 4;
    private final static int PRINT = 5;
    private final static int END = 99;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static void run() throws Exception {

        int cmd, data, num, ret;
        int[] output = new int[MAX_NODE];
        String str;
        StringTokenizer st;

        while(true) {
            str = br.readLine();
            st = new StringTokenizer(str, " ");
            cmd = Integer.parseInt(st.nextToken());

            switch(cmd) {
                case ADD_HEAD :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Head(data);
                    break;

                case ADD_TAIL :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Tail(data);
                    break;

                case ADD_NUM :
                    data = Integer.parseInt(st.nextToken());
                    num = Integer.parseInt(st.nextToken());
                    usersolution.addNode2Num(data, num);
                    break;

                case REMOVE :
                    data = Integer.parseInt(st.nextToken());
                    usersolution.removeNode(data);
                    break;

                case PRINT :
                    ret = usersolution.getList(output);
                    for(int i = 0; i < ret; i++) {
                        System.out.print(output[i] + " ");
                    }
                    System.out.println();
                    break;

                case END :
                    return;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        int TC;
        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            System.out.println("#" + tc);
            usersolution.init();
            run();
            System.out.println();
        }
    }

}