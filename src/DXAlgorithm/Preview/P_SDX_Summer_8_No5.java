package DXAlgorithm.Preview;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class P_SDX_Summer_8_No5 {

    private static class UserSolution {

        Node[] users;
        int userCnt;
        LinkedList list;

        private class LinkedList {
            Node head;
            Node tail;

            public LinkedList() {
                this.head = new Node(new User(), null, null);
                this.tail = new Node(new User(), null, null);
                this.head.next = this.tail;
                this.tail.prev = this.head;
            }

            boolean isEmpty() {
                Node cur = head.next;
                if (cur != tail) return false;
                else return true;
            }

            void add(int idx, Node node) {
                int cnt = 0;
                Node cur = this.head.next;
                if (idx == 0) addFirst(node);
                else {
                    while (cur != tail) {
                        if (cnt == idx) {
                            cur.prev.next = node;
                            node.prev = cur.prev;
                            cur.prev = node;
                            node.next = cur;
                        }

                        cnt++;
                        cur = cur.next;
                    }
                }
            }

            void addFirst(Node node) {
                node.next = this.head.next;
                this.head.next.prev = node;
                this.head.next = node;
                node.prev = this.head;
            }

            void addLast(Node node) {
                this.tail.prev.next = node;
                node.prev = this.tail.prev;
                this.tail.prev = node;
                node.next = this.tail;
            }

            int size() {
                int size = 0;
                Node cur = head.next;
                while (cur != tail) {
                    size++;
                    cur = cur.next;
                    if (size > 10) return 10;
                }
                return size;
            }

            Node get(int idx) {
                int cnt = 0;
                Node cur = head.next;
                while (cur != tail) {
                    if (cnt == idx) return cur;
                    cnt++;
                    cur = cur.next;
                }

                return cur;
            }

            void print() {
                Node cur = head.next;
                while (cur != tail) {
                    System.out.print(cur.user.uId + "(" + cur.user.income + ") ");
                    cur = cur.next;
                }
                System.out.println();
            }
        }

        private class Node {
            User user;
            Node prev;
            Node next;

            public Node(User user, Node prev, Node next) {
                this.user = user;
                this.prev = prev;
                this.next = next;
            }
        }

        private class User {
            int uId;
            int income;

            public User() {
                this.uId = 0;
                this.income = 0;
            }

            public User(int uId, int income) {
                this.uId = uId;
                this.income = income;
            }
        }

        public void init() {
            users = new Node[100000];
            list = new LinkedList();
            userCnt = 0;
        }

        public void addUser(int uID, int income) {
            Node node = new Node(new User(uID, income), null, null);
            users[userCnt++] = node;

            if (list.isEmpty()) list.addFirst(node);
            else {
                boolean findLoc = false;
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).user.income < income) {
                        list.add(i, node);
                        findLoc = true;
                        break;
                    }
                    else if (list.get(i).user.income == income) {
                        int idx = i;
                        while (list.get(idx).user.income == income) {
                            if (list.get(idx).user.uId > uID) break;
                            idx++;
                        }
                        list.add(idx, node);
                        findLoc = true;
                        break;
                    }
                }
                if (!findLoc && list.size() < 10) {
                    list.addLast(node);
                }
            }
        }


        int getTop10(int[] result) {
            for (int i = 0; i < list.size(); i++) result[i] = list.get(i).user.uId;
            return list.size();
        }
    }


    private final static int MAX_INPUT = 100000;
    private final static int MAX_NUM = 30000;

    private final static UserSolution usersolution = new UserSolution();

    private static BufferedReader br;

    private static int[] input = new int[MAX_INPUT];
    private static long seed = 13410;

    private static long pseudoRand() {
        seed = (seed * 214013 + 2531011) & 0xffffffffL;
        return (seed>>11) % MAX_NUM;
    }

    private static void makeInput(int inputLen) {
        for(int i = 0; i < inputLen; i++) {
            input[i] = (int)(pseudoRand());
        }
    }

    private static int run() throws Exception {
        int score = 100;
        int N, userNum, uID = 0, ret, sum, ans;
        int[] result = new int[10];
        String str;

        str = br.readLine();
        N = Integer.parseInt(str);

        for(int i = 0; i < N; i++) {
            str = br.readLine();
            userNum = Integer.parseInt(str);
            makeInput(userNum);

            for(int j = 0; j < userNum; j++) {
                usersolution.addUser(uID++, input[j]);
            }
            ret = usersolution.getTop10(result);

            sum = 0;
            for(int j = 0; j < ret; j++) {
                sum += result[j];
            }

            str = br.readLine();
            ans = Integer.parseInt(str);
            if(sum != ans) {
                score = 0;
            }
        }
        return score;
    }
    public static void main(String[] args) throws Exception {
        int TC;
        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        TC = Integer.parseInt(str);

        for (int tc = 1; tc <= TC; tc++) {
            usersolution.init();
            System.out.println("#" + tc + " " + run());
        }
    }
}