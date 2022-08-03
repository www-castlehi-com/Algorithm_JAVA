package DXAlgorithm.CodeBattle;

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class P_SDX_Summer_11_No3 {

    private static class UserSolution {

        static int strlen(char str[]) {
            int cnt = 0;
            for (int i = 0; str[i] != '\0'; i++) cnt++;
            return cnt;
        }

        private class Trie {
            Node root;

            public Trie() {
                root = new Node();
            }

            public int contains(char word[]) {
                return contains(this.root, word, 0);
            }

            public int contains(Node node, char word[], int idx) {
                if (idx == strlen(word)) {
                    if (node.lastChar) return node.count;
                    else return 0;
                }

                char ch = word[idx];

                int cnt = 0;
                if (ch != '?') {
                    Node child = node.children.get(ch);
                    if (child == null) return 0;
                    else {
                        cnt = contains(child, word, idx + 1);
                    }
                }
                else {
                    for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
                        cnt += contains(entry.getValue(), word, idx + 1);
                    }
                }

                return cnt;
            }

            public void insert(char word[], int count) {
                Node cur = this.root;

                for (int i = 0; i < strlen(word); i++) {
                    cur = cur.children.computeIfAbsent(word[i], c -> new Node());
                }
                cur.lastChar = true;
                cur.count = count;
            }

            public void delete(Node node, char word[], int idx) {
                char ch = word[idx];

                if (node.children.containsKey(ch) || ch == '?') {
                    if (ch != '?') {
                        Node child = node.children.get(ch);

                        idx++;

                        if (idx == strlen(word)) {
                            if (child.lastChar) {
                                child.lastChar = false;
                                if (child.children.isEmpty()) {
                                    node.children.remove(ch);
                                }
                            }
                        } else {
                            delete(child, word, idx);

                            if (!child.lastChar && child.children.isEmpty()) {
                                node.children.remove(ch);
                            }
                        }
                    }
                    else {
                        idx++;

                        for (Map.Entry<Character, Node> entry : node.children.entrySet()) {
                            Node child = entry.getValue();

                            if (idx == strlen(word)) {
                                if (child.lastChar) {
                                    child.lastChar = false;
                                    if (child.children.isEmpty()) {
                                        node.children.remove(ch);
                                    }
                                }
                            } else {
                                delete(child, word, idx);

                                if (!child.lastChar && child.children.isEmpty()) {
                                    node.children.remove(ch);
                                }
                            }
                        }
                    }
                }
            }
        }

        private class Node {
            HashMap<Character, Node> children;
            boolean lastChar;
            int count;

            public Node() {
                this.children = new HashMap<>();
                count = 0;
            }

            public Node(int count) {
                this.children = new HashMap<>();
                this.count = count;
            }
        }

        static Trie trie;

        void init() {
            trie = new Trie();
        }

        int add(char str[]) {
            trie.insert(str, trie.contains(str) + 1);
            return trie.contains(str);
        }

        int remove(char str[]) {
            int result = trie.contains(str);
            if (result == -1) return 0;
            else {
                trie.delete(trie.root, str, 0);
                return result;
            }
        }

        int search(char str[]) {
            int result = trie.contains(str);
            if (result == -1) return 0;
            else return result;
        }
    }


    private final static int MAX_LEN = 30;
    private final static int CMD_INIT = 1;
    private final static int CMD_ADD = 2;
    private final static int CMD_REMOVE = 3;
    private final static int CMD_SEARCH = 4;

    private final static UserSolution usersolution = new UserSolution();

    private static void String2Char(char[] buf, String str) {
        for (int i = 0; i < str.length(); ++i)
            buf[i] = str.charAt(i);
        buf[str.length()] = '\0';
    }

    private static boolean run(BufferedReader br) throws Exception {
        int q = Integer.parseInt(br.readLine());

        char[] str = new char[MAX_LEN + 1];
        int cmd, ans, ret = 0;
        boolean okay = false;

        for (int i = 0; i < q; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            cmd = Integer.parseInt(st.nextToken());
            switch (cmd) {
                case CMD_INIT:
                    usersolution.init();
                    okay = true;
                    break;
                case CMD_ADD:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.add(str);
                    if (ret != ans)
                        okay = false;
//                    System.out.println("okay = " + okay);
                    break;
                case CMD_REMOVE:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.remove(str);
                    if (ret != ans)
                        okay = false;
//                    System.out.println("okay = " + okay);
                    break;
                case CMD_SEARCH:
                    String2Char(str, st.nextToken());
                    ans = Integer.parseInt(st.nextToken());
                    ret = usersolution.search(str);
                    if (ret != ans)
                        okay = false;
//                    System.out.println("okay = " + okay);
                    break;
                default:
                    okay = false;
                    break;
            }
        }
        return okay;
    }

    public static void main(String[] args) throws Exception {
        int TC, MARK;

        //System.setIn(new java.io.FileInputStream("res/sample_input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        TC = Integer.parseInt(st.nextToken());
        MARK = Integer.parseInt(st.nextToken());

        for (int testcase = 1; testcase <= TC; ++testcase) {
            int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
        }

        br.close();
    }
}