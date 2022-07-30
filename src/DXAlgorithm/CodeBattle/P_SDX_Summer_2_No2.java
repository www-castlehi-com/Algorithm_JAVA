package DXAlgorithm.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P_SDX_Summer_2_No2 {
    private static class Node {
        public int data;
        public Node prev;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    public static class UserSolution {

        private final static int MAX_NODE = 10000;

        private Node[] node = new Node[MAX_NODE];
        private int nodeCnt = 0;
        private Node head;

        public Node getNode(int data) {
            node[nodeCnt] = new Node(data);
            return node[nodeCnt++];
        }

        public void init() {
            node = new Node[MAX_NODE];
            nodeCnt = 0;
        }

        public void addNode2Head(int data) {
            Node[] copyNode = new Node[MAX_NODE];
            int copyNodeCnt = 0;
            copyNode[copyNodeCnt++] = new Node(data);
            for (int i = 0; i < nodeCnt; i++) {
                copyNode[copyNodeCnt++] = node[i];
                if (i == 0) {
                    copyNode[0].next = copyNode[1];
                    copyNode[1].prev = copyNode[0];
                }
            }
            nodeCnt = copyNodeCnt;
            node = copyNode;
            head = node[0];
        }

        public void addNode2Tail(int data) {
            getNode(data);
            if (nodeCnt - 2 >= 0) {
                node[nodeCnt - 2].next = node[nodeCnt - 1];
                node[nodeCnt - 1].prev = node[nodeCnt - 2];
            }
        }

        public void addNode2Num(int data, int num) {
            num--;

            Node[] copyNode = new Node[MAX_NODE];
            int copyNodeCnt = 0;
            for (int i = 0; i < num; i++) {
                copyNode[copyNodeCnt++] = node[i];
            }
            copyNode[copyNodeCnt++] = new Node(data);
            for (int i = num; i < nodeCnt; i++) {
                copyNode[copyNodeCnt++] = node[i];
            }

            if (num + 1 < copyNodeCnt) {
                copyNode[num].next = copyNode[num + 1];
                copyNode[num + 1].prev = copyNode[num];
            }
            if (num - 1 >= 0) {
                copyNode[num].prev = copyNode[num - 1];
                copyNode[num - 1].next = copyNode[num];
            }

            nodeCnt = copyNodeCnt;
            node = copyNode;
            head = node[0];
        }

        public int findNode(int data) {
            for (int i = 0; i < nodeCnt; i++) {
                if (node[i].data == data) return i + 1;
            }
            return Integer.MIN_VALUE + 1;
        }

        public void removeNode(int data) {
            int findIdx = findNode(data) - 1;

            if (findIdx >= 0) {
                if (findIdx == 0) {
                    for (int i = 0; i < nodeCnt - 1; i++) {
                        node[i] = node[i + 1];
                    }
                    node[0].prev = null;
                } else if (findIdx == nodeCnt - 1) {
                    node[nodeCnt - 2].next = null;
                } else {
                    node[findIdx - 1].next = node[findIdx + 1];
                    node[findIdx + 1].prev = node[findIdx - 1];
                    for (int i = findIdx; i < nodeCnt - 1; i++) {
                        node[i] = node[i + 1];
                    }
                }
                nodeCnt--;
                head = node[0];
            }
        }

        public int getList(int[] output) {
            for (int i = 0; i < nodeCnt; i++) {
                output[i] = node[i].data;
            }
            return nodeCnt;
        }

        public int getReversedList(int[] output) {
            int cnt = 0;
            for (int i = nodeCnt - 1; i >= 0; i--) {
                output[cnt++] = node[i].data;
            }
            return nodeCnt;
        }
    }

    private final static int MAX_NODE = 10000;
    private final static int ADD_HEAD = 1;
    private final static int ADD_TAIL = 2;
    private final static int ADD_NUM = 3;
    private final static int FIND = 4;
    private final static int REMOVE = 5;
    private final static int PRINT = 6;
    private final static int PRINT_REVERSE = 7;
    private final static int END = 99;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    public static void run() throws Exception {
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

                case FIND :
                    data = Integer.parseInt(st.nextToken());
                    num = usersolution.findNode(data);
                    System.out.println(num);
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

                case PRINT_REVERSE :
                    ret = usersolution.getReversedList(output);
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