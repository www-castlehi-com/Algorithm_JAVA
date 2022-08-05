package DXAlgorithm.review;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_12372 {

    static public class UserSolution {

        LinkedList top10;
        Node[] users;
        int userCount;

        private class LinkedList {
            Node head;
            Node tail;

            public LinkedList() {
                this.head = new Node(new User(), null, null);
                this.tail = new Node(new User(), null, null);

                this.head.next = this.tail;
                this.tail.prev = this.head;
            }

            public void addFirst (Node node) {
                node.next = this.head.next;
                this.head.next.prev = node;
                this.head.next = node;
                node.prev = this.head;
            }

            public void addLast (Node node) {
                node.prev = this.tail.prev;
                this.tail.prev.next = node;
                this.tail.prev = node;
                node.next = this.tail;
            }

            public void add(int idx, Node node) {
                Node cur = this.get(idx);

                cur.prev.next = node;
                node.prev = cur.prev;
                cur.prev = node;
                node.next = cur;
            }

            public boolean empty() {
                Node cur = this.head.next;

                if (cur != this.tail) return false;
                return true;
            }

            public int size() {
                Node cur = this.head.next;

                int cnt = 0;
                while (cur != this.tail) {
                    cnt++;
                    cur = cur.next;
                    if (cnt > 10) return 10;
                }
                return cnt;
            }

            public Node get(int idx) {
                Node cur = this.head.next;

                int cnt = 0;
                while (cur != this.tail) {
                    if (cnt == idx) return cur;
                    cur = cur.next;
                    cnt++;
                }
                return cur;
            }
        }

        private class Node {
            User user;
            Node next;
            Node prev;

            public Node(User user, Node next, Node prev) {
                this.user = user;
                this.next = next;
                this.prev = prev;
            }
        }

        private class User {
            int uId;
            int income;

            public User() {
            }

            public User(int uId, int income) {
                this.uId = uId;
                this.income = income;
            }
        }

        public void init() {
            top10 = new LinkedList();
            users = new Node[100000];
            userCount = 0;
        }

        public void addUser(int uID, int income) {
            Node node = new Node(new User(uID, income), null, null);

            users[userCount++] = node;
            if (top10.empty()) top10.addFirst(node);
            else {
                boolean insert = false;
                for (int i = 0; i < top10.size(); i++) {
                    User user = top10.get(i).user;
                    if (user.income < income) {
                        top10.add(i, node);
                        insert = true;
                        break;
                    }
                    else if (user.income == income) {
                        int j = i;
                        while (top10.get(j).user.income == income) {
                            if (top10.get(j).user.uId > uID) break;
                            j++;
                        }
                        top10.add(j, node);
                        insert = true;
                        break;
                    }
                }

                if (!insert && top10.size() < 10) top10.addLast(node);
            }
        }

        int getTop10(int[] result) {
            for (int i = 0; i < top10.size(); i++) result[i] = top10.get(i).user.uId;
            return top10.size();
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