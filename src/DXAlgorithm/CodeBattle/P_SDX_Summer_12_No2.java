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
import java.util.Scanner;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class P_SDX_Summer_12_No2
{

    private static class Node {
        long odd;
        long even;

        public Node() {
        }

        public Node(long odd, long even) {
            this.odd = odd;
            this.even = even;
        }
    }

    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            int q = sc.nextInt();


            int size = (int) Math.pow(2, Math.ceil(Math.log(n) / Math.log(2)));
            int[] a = new int[size];
            for (int i = 0; i < n; i++) a[i] = sc.nextInt();

            Node[] segTree = new Node[size * 2];
            setTree(size, segTree, a);

            System.out.print("#" + test_case + " ");
            for (int i = 0; i < q; i++) {
                int com = sc.nextInt();

                switch (com) {
                    case 1:
                        int l = sc.nextInt();
                        int r = sc.nextInt() - 1;
                        long result = findResult(size, segTree, l, r);
                        System.out.print(result + " ");
                        break;
                    case 0:
                        int idx = sc.nextInt();
                        int x = sc.nextInt();
                        changeTree(size, segTree, idx, x);

                        break;
                }
            }
            System.out.println();
        }
    }

    private static void changeTree(int size, Node[] segTree, int idx, int x) {
        if (idx % 2 == 0) segTree[idx + size] = new Node(x, -x);
        else segTree[idx + size] = new Node(-x, x);
        idx += size;

        while ((idx >>= 1) != 0) {
            segTree[idx].even = segTree[idx << 1].even + segTree[idx << 1 | 1].even;
            segTree[idx].odd = segTree[idx << 1].odd + segTree[idx << 1 | 1].odd;
        }
    }

    private static long findResult(int size, Node[] segTree, int l, int r) {
        long result = 0;
        if ((l + 1) % 2 == 0) {
            for (l += size, r += size; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1) result += segTree[l++].even;
                if ((r & 1) == 0) result += segTree[r--].even;
            }

            if (l == r) result += segTree[l].even;
        }
        else {
            for (l += size, r += size; l < r; l >>= 1, r >>= 1) {
                if ((l & 1) == 1) result += segTree[l++].odd;
                if ((r & 1) == 0) result += segTree[r--].odd;
            }

            if (l == r) result += segTree[l].odd;
        }

        return result;
    }

    private static void setTree(int size, Node[] segTree, int[] a) {
        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) segTree[i + size] = new Node(a[i], -a[i]);
            else segTree[i + size] = new Node(-a[i], a[i]);
        }

        for (int i = size - 1; i > 0; i--) {
            segTree[i] = new Node();
            segTree[i].odd = segTree[i << 1].odd + segTree[i << 1 | 1].odd;
            segTree[i].even = segTree[i << 1].even + segTree[i << 1 | 1].even;
        }
    }
}